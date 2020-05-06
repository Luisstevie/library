package com.color.sms.messages.theme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Theme implements Parcelable {
    private int themeMain;
    private int themeCompose;

    protected Theme(Parcel in) {
        themeMain = in.readInt();
        themeCompose = in.readInt();
    }

    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };

    public Theme(int themeMain, int themeCompose) {
        this.themeMain = themeMain;
        this.themeCompose = themeCompose;
    }

    public int getThemeMain() {
        return themeMain;
    }

    public int getThemeCompose() {
        return themeCompose;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(themeMain);
        dest.writeInt(themeCompose);
    }
}
