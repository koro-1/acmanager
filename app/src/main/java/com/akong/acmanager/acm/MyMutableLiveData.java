package com.akong.acmanager.acm;


import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class MyMutableLiveData<T> extends MutableLiveData<T> {

    //默认未非粘性
    private boolean isStick = false;

    public MyMutableLiveData(boolean isStick) {
        this.isStick = isStick;
    }

    public MyMutableLiveData() {
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);

        if (!isStick) {
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * hook技术的实现方法  拦截onChange方法的执行
     *
     * @param observer
     */
    private void hook(Observer<? super T> observer) throws Exception {
        //获取到LiveData的类对象
        Class<LiveData> liveDataClass = LiveData.class;
        //根据类对象获取到mVersion的反射对象
        Field mVersionField = liveDataClass.getDeclaredField("mVersion");
        //打开权限
        mVersionField.setAccessible(true);
        //获取到mObservers的反射对象
        Field mObserversField = liveDataClass.getDeclaredField("mObservers");
        //打开权限
        mObserversField.setAccessible(true);
        //从当前的LiveData对象中获取mObservers这个成员变量在当前对象中的值
        Object mObservers = mObserversField.get(this);
        //获取到mObservers这个map的get方法
        Method get = mObservers.getClass().getDeclaredMethod("get", Object.class);
        //打开权限
        get.setAccessible(true);
        // 执行get方法
        Object invokeEntry = get.invoke(mObservers, observer);
        //定义一个空对象   LifecycleBoundObserver
        Object observerWrapper = null;
        if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
            observerWrapper = ((Map.Entry) invokeEntry).getValue();
        }
        if (observerWrapper == null) {
            throw new NullPointerException("ObserverWrapper不能为空");
        }

        //得到observerWrapper的类对象
        Class<?> aClass = observerWrapper.getClass().getSuperclass();
        //获取mLastVersion的发射对象
        Field mLastVersionField = aClass.getDeclaredField("mLastVersion");
        //打开权限
        mLastVersionField.setAccessible(true);
        //获取到mVersion的值
        Object o = mVersionField.get(this);
        //把它的值赋值给mLastVersion
        mLastVersionField.set(observerWrapper, o);
    }


}