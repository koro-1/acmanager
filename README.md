<p align="center">
  <img src="https://raw.githubusercontent.com/xuexiangjys/XUI/master/art/app_logo_xui.png" width="388" height="321" alt="Banner" />
</p>

# Account Manager
[![](https://jitpack.io/v/koro-1/acmanager.svg)](https://jitpack.io/#koro-1/acmanager)
[![api](https://img.shields.io/badge/API-17+-brightgreen.svg)](https://android-arsenal.com/api?level=17)
[![Issue](https://img.shields.io/github/issues/koro-1/acmanager.svg)](https://github.com/koro-1/acmanager/issues)
[![Star](https://img.shields.io/github/stars/koro-1/acmanager.svg)](https://github.com/koro-1/acmanager)

## [英文](./README.md) | [中文](./README_ZH.md)

一个简洁而又优雅的Android原生账号管理框架，解放你的双手！还不赶紧点击[使用说明文档](https://github.com/koro-1/acmanager/wiki)，体验一下吧！


在提issue前，请先阅读[【提问的智慧】](https://xuexiangjys.blog.csdn.net/article/details/83344235)，并严格按照[issue模板](https://github.com/koro-1/acmanager/issues/new/choose)进行填写，节约大家的时间。

在使用前，请一定要仔细阅读[使用说明文档](https://github.com/koro-1/acmanager/wiki),重要的事情说三遍！！！

在使用前，请一定要仔细阅读[使用说明文档](https://github.com/koro-1/acmanager/wiki),重要的事情说三遍！！！

在使用前，请一定要仔细阅读[使用说明文档](https://github.com/koro-1/acmanager/wiki),重要的事情说三遍！！！

## 关于我

一个软件工程专业大四毕业生， 24年入职浙江一家中厂 。欢迎技术交流

## 快速集成

为了方便大家快速集成X系列框架库，我提供了一个空壳模版供大家参考使用: [https://github.com/xuexiangjys/TemplateAppProject](https://github.com/xuexiangjys/TemplateAppProject)



----

## 特征

* 简洁优雅，尽可能少得引用资源文件的数量，项目库整体大小不足1M(打包后大约644k）。



* 使用简单，为方便快速开发，提高开发效率，对api进行了优化，提供一键式接入。



## Star趋势

[![Stargazers over time](https://starchart.cc/koro-1/acmanager.svg)](https://starchart.cc/koro-1/acmanager)

----

## 如何使用

> 在决定使用acmanager前，你必须明确的一点是，此框架给出的是一整套账号管理解决方案，

### 添加Gradle依赖

1.先在项目根目录的 build.gradle 的 repositories 添加:
```
allprojects {
     repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

2.然后在应用项目(一般是app)的 `build.gradle` 的 dependencies 添加:

```
dependencies {
  ...
  //androidx项目
    implementation 'com.github.koro-1:acmanager:1.0.1'

  
}
```



## 演示

### 演示程序截图



### Demo下载

> 演示程序大概18M（主要是demo中集成了一个小视频拍摄的库比较大，大约13M左右，而XUI库目前只有644k大小），项目比较大，推荐使用蒲公英下载。

![xui_size.png](./art/xui_size.png)

#### 蒲公英下载

> 蒲公英下载的密码: xuexiangjys

[![蒲公英](https://img.shields.io/badge/downloads-蒲公英-blue.svg)](https://www.pgyer.com/XUIDemo)

[![download_pugongying.png](./art/download_pugongying.png)](https://www.pgyer.com/XUIDemo)

#### Github下载

[![Github](https://img.shields.io/badge/downloads-Github-blue.svg)](https://github.com/koro-1/acmanager/blob/master/apk/xuidemo.apk?raw=true)

[![download_github.png](./art/download_github.png)](https://github.com/koro-1/acmanager/blob/master/apk/xuidemo.apk?raw=true)

## 贡献代码

> 由于本人精力有限，现欢迎大家踊跃贡献自己的idea，你将有机会参与到github上star过千项目的维护中，提升自己的行业影响力！

代码贡献要求：

* 请保持现有的代码样式，而不是根据您的习惯。请遵守阿里巴巴Java编码规范。

* 只需修改你确定需要优化的代码，而不是所有与你想法不同的代码。

* 在启动pull请求之前，应该充分测试提交代码。

* 请将新代码提交到dev分支，而不是主分支。

## 特别感谢

* [QMUI_Android](https://github.com/Tencent/QMUI_Android)
* [AgentWeb](https://github.com/Justson/AgentWeb)
* [Android-Iconics](https://github.com/mikepenz/Android-Iconics)
* [Android-PickerView](https://github.com/Bigkoo/Android-PickerView)
* [CityPicker](https://github.com/xuexiangjys/CityPicker)
* [ELinkageScroll](https://github.com/MFC-TEC/ELinkageScroll)
* [FlycoBanner_Master](https://github.com/H07000223/FlycoBanner_Master)
* [Linkage-RecyclerView](https://github.com/KunMinX/Linkage-RecyclerView)
* [MaterialEditText](https://github.com/rengwuxian/MaterialEditText)
* [MaterialSpinner](https://github.com/jaredrummler/MaterialSpinner)
* [MaterialProgressBar](https://github.com/DreaminginCodeZH/MaterialProgressBar)
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
* [PictureSelector](https://github.com/LuckSiege/PictureSelector)
* [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
* [SlideBack](https://github.com/ParfoisMeng/SlideBack)
* [SwipeRecyclerView](https://github.com/yanzhenjie/SwipeRecyclerView)

