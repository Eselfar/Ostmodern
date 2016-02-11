package boulier.remi.ostmodern.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class Item {
    @SerializedName("link_url")
    @Expose
    private Object linkUrl;
    @SerializedName("sub_heading")
    @Expose
    private Object subHeading;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("link_title")
    @Expose
    private Object linkTitle;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("content_url")
    @Expose
    private String contentUrl;
    @SerializedName("schedule_url")
    @Expose
    private String scheduleUrl;
    @SerializedName("set_url")
    @Expose
    private String setUrl;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("position")
    @Expose
    private Long position;
    @SerializedName("heading")
    @Expose
    private String heading;

    public Object getLinkUrl() {
        return linkUrl;
    }

    public Object getSubHeading() {
        return subHeading;
    }

    public String getUid() {
        return uid;
    }

    public Object getLinkTitle() {
        return linkTitle;
    }

    public String getSelf() {
        return self;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public String getSetUrl() {
        return setUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getPosition() {
        return position;
    }

    public String getHeading() {
        return heading;
    }


}
