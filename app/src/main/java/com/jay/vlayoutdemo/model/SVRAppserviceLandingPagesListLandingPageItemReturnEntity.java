package com.jay.vlayoutdemo.model;

import java.io.Serializable;

/**
 * Created by imaginato on 2015/7/22.
 * {"landingList":[{"pageId":"3","title":"","image":"","badgeValue":"","badgeType":""}],"status":1}
 */
public class SVRAppserviceLandingPagesListLandingPageItemReturnEntity implements Serializable {
    private String pageId;
    private String title;
    private String image;
    private String badgeValue;
    private String badgeType;
    private String heading;
    private String standalone;

    public String getStandalone() {return standalone;}

    public void setStandalone(String standalone) {this.standalone = standalone;}

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBadgeValue() {
        return badgeValue;
    }

    public void setBadgeValue(String badgeValue) {
        this.badgeValue = badgeValue;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
