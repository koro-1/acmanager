package com.akong.acmanager.acm;

/**
 * Class exposing authenticated user details to the UI.
 */
public class LoggedInUserView {
    public LoggedInUserView() {
    }

    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}