package boulier.remi.ostmodern.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Remi BOULIER on 12/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class ImageDetails {

    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("show")
    @Expose
    public Boolean show;
    @SerializedName("schedule_urls")
    @Expose
    public List<String> scheduleUrls;
    @SerializedName("content_url")
    @Expose
    public String contentUrl;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("image_type_url")
    @Expose
    public String imageTypeUrl;
    @SerializedName("upload_image_url")
    @Expose
    public String uploadImageUrl;
    @SerializedName("language_modified_by")
    @Expose
    public Object languageModifiedBy;
    @SerializedName("language_version_url")
    @Expose
    public String languageVersionUrl;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("language_ends_on")
    @Expose
    public Object languageEndsOn;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("image_type")
    @Expose
    public String imageType;
    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("created_by")
    @Expose
    public Object createdBy;
    @SerializedName("language_publish_on")
    @Expose
    public String languagePublishOn;
    @SerializedName("language_version_number")
    @Expose
    public Long languageVersionNumber;
    @SerializedName("language_modified")
    @Expose
    public String languageModified;
    @SerializedName("position")
    @Expose
    public Long position;
    @SerializedName("align")
    @Expose
    public String align;

    public String getUid() {
        return uid;
    }

    public Boolean getShow() {
        return show;
    }

    public List<String> getScheduleUrls() {
        return scheduleUrls;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getImageTypeUrl() {
        return imageTypeUrl;
    }

    public String getUploadImageUrl() {
        return uploadImageUrl;
    }

    public Object getLanguageModifiedBy() {
        return languageModifiedBy;
    }

    public String getLanguageVersionUrl() {
        return languageVersionUrl;
    }

    public String getDescription() {
        return description;
    }

    public Object getLanguageEndsOn() {
        return languageEndsOn;
    }

    public String getCreated() {
        return created;
    }

    public String getImageType() {
        return imageType;
    }

    public String getSelf() {
        return self;
    }

    public String getTitle() {
        return title;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public String getLanguagePublishOn() {
        return languagePublishOn;
    }

    public Long getLanguageVersionNumber() {
        return languageVersionNumber;
    }

    public String getLanguageModified() {
        return languageModified;
    }

    public Long getPosition() {
        return position;
    }

    public String getAlign() {
        return align;
    }
}
