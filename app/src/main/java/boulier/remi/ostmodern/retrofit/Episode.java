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
public class Episode implements Parcelable{
    @SerializedName("subtitle")
    @Expose
    private Object subtitle;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("schedule_urls")
    @Expose
    private List<String> scheduleUrls;
    @SerializedName("image_urls")
    @Expose
    private List<String> imageUrls;
    @SerializedName("publish_on")
    @Expose
    private String publishOn;
    @SerializedName("talent_urls")
    @Expose
    private List<Object> talentUrls;
    @SerializedName("schedule_url")
    @Expose
    private String scheduleUrl;
    @SerializedName("plan_urls")
    @Expose
    private List<Object> planUrls;
    @SerializedName("language_publish_on")
    @Expose
    private String languagePublishOn;
    @SerializedName("episode_number")
    @Expose
    private Object episodeNumber;
    @SerializedName("language_modified_by")
    @Expose
    private Object languageModifiedBy;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("language_version_url")
    @Expose
    private String languageVersionUrl;
    @SerializedName("version_number")
    @Expose
    private Long versionNumber;
    @SerializedName("modified_by")
    @Expose
    private Object modifiedBy;
    @SerializedName("language_ends_on")
    @Expose
    private String languageEndsOn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("items")
    @Expose
    private List<String> items;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("created_by")
    @Expose
    private Object createdBy;
    @SerializedName("tag_urls")
    @Expose
    private List<Object> tagUrls;
    @SerializedName("ends_on")
    @Expose
    private String endsOn;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("version_url")
    @Expose
    private String versionUrl;
    @SerializedName("parent_url")
    @Expose
    private Object parentUrl;
    @SerializedName("language_version_number")
    @Expose
    private Long languageVersionNumber;
    @SerializedName("language_modified")
    @Expose
    private String languageModified;

    protected Episode(Parcel in) {
        uid = in.readString();
        scheduleUrls = in.createStringArrayList();
        imageUrls = in.createStringArrayList();
        publishOn = in.readString();
        scheduleUrl = in.readString();
        languagePublishOn = in.readString();
        slug = in.readString();
        languageVersionUrl = in.readString();
        languageEndsOn = in.readString();
        title = in.readString();
        items = in.createStringArrayList();
        self = in.readString();
        created = in.readString();
        modified = in.readString();
        endsOn = in.readString();
        synopsis = in.readString();
        versionUrl = in.readString();
        languageModified = in.readString();
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    public Object getSubtitle() {
        return subtitle;
    }

    public String getUid() {
        return uid;
    }

    public List<String> getScheduleUrls() {
        return scheduleUrls;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public List<Object> getTalentUrls() {
        return talentUrls;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public List<Object> getPlanUrls() {
        return planUrls;
    }

    public String getLanguagePublishOn() {
        return languagePublishOn;
    }

    public Object getEpisodeNumber() {
        return episodeNumber;
    }

    public Object getLanguageModifiedBy() {
        return languageModifiedBy;
    }

    public String getSlug() {
        return slug;
    }

    public String getLanguageVersionUrl() {
        return languageVersionUrl;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public Object getModifiedBy() {
        return modifiedBy;
    }

    public String getLanguageEndsOn() {
        return languageEndsOn;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getItems() {
        return items;
    }

    public String getSelf() {
        return self;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public List<Object> getTagUrls() {
        return tagUrls;
    }

    public String getEndsOn() {
        return endsOn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public Object getParentUrl() {
        return parentUrl;
    }

    public Long getLanguageVersionNumber() {
        return languageVersionNumber;
    }

    public String getLanguageModified() {
        return languageModified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeStringList(scheduleUrls);
        dest.writeStringList(imageUrls);
        dest.writeString(publishOn);
        dest.writeString(scheduleUrl);
        dest.writeString(languagePublishOn);
        dest.writeString(slug);
        dest.writeString(languageVersionUrl);
        dest.writeString(languageEndsOn);
        dest.writeString(title);
        dest.writeStringList(items);
        dest.writeString(self);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeString(endsOn);
        dest.writeString(synopsis);
        dest.writeString(versionUrl);
        dest.writeString(languageModified);
    }
}
