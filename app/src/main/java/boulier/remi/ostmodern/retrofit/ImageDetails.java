package boulier.remi.ostmodern.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Remi BOULIER on 12/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class ImageDetails implements Parcelable{

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("show")
    @Expose
    private Boolean show;
    @SerializedName("schedule_urls")
    @Expose
    private List<String> scheduleUrls;
    @SerializedName("content_url")
    @Expose
    private String contentUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image_type_url")
    @Expose
    private String imageTypeUrl;
    @SerializedName("upload_image_url")
    @Expose
    private String uploadImageUrl;
    @SerializedName("language_modified_by")
    @Expose
    private Object languageModifiedBy;
    @SerializedName("language_version_url")
    @Expose
    private String languageVersionUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language_ends_on")
    @Expose
    private Object languageEndsOn;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("image_type")
    @Expose
    private String imageType;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_by")
    @Expose
    private Object createdBy;
    @SerializedName("language_publish_on")
    @Expose
    private String languagePublishOn;
    @SerializedName("language_version_number")
    @Expose
    private Long languageVersionNumber;
    @SerializedName("language_modified")
    @Expose
    private String languageModified;
    @SerializedName("position")
    @Expose
    private Long position;
    @SerializedName("align")
    @Expose
    private String align;

    protected ImageDetails(Parcel in) {
        uid = in.readString();
        scheduleUrls = in.createStringArrayList();
        contentUrl = in.readString();
        url = in.readString();
        imageTypeUrl = in.readString();
        uploadImageUrl = in.readString();
        languageVersionUrl = in.readString();
        description = in.readString();
        created = in.readString();
        imageType = in.readString();
        self = in.readString();
        title = in.readString();
        languagePublishOn = in.readString();
        languageModified = in.readString();
        align = in.readString();
    }

    public static final Creator<ImageDetails> CREATOR = new Creator<ImageDetails>() {
        @Override
        public ImageDetails createFromParcel(Parcel in) {
            return new ImageDetails(in);
        }

        @Override
        public ImageDetails[] newArray(int size) {
            return new ImageDetails[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeStringList(scheduleUrls);
        dest.writeString(contentUrl);
        dest.writeString(url);
        dest.writeString(imageTypeUrl);
        dest.writeString(uploadImageUrl);
        dest.writeString(languageVersionUrl);
        dest.writeString(description);
        dest.writeString(created);
        dest.writeString(imageType);
        dest.writeString(self);
        dest.writeString(title);
        dest.writeString(languagePublishOn);
        dest.writeString(languageModified);
        dest.writeString(align);
    }
}
