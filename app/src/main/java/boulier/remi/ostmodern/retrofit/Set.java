package boulier.remi.ostmodern.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class Set {
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("schedule_urls")
    @Expose
    private List<String> scheduleUrls;
    @SerializedName("publish_on")
    @Expose
    private String publishOn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("set_type_url")
    @Expose
    private String setTypeUrl;
    @SerializedName("temp_image")
    @Expose
    private String tempImage;
    @SerializedName("film_count")
    @Expose
    private Long filmCount;
    @SerializedName("image_urls")
    @Expose
    private List<String> imageUrls;
    @SerializedName("hierarchy_url")
    @Expose
    private Object hierarchyUrl;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("version_number")
    @Expose
    private Long versionNumber;
    @SerializedName("language_ends_on")
    @Expose
    private String languageEndsOn;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("items")
    @Expose
    private List<Item> items;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("ends_on")
    @Expose
    private String endsOn;
    @SerializedName("version_url")
    @Expose
    private String versionUrl;
    @SerializedName("set_type_slug")
    @Expose
    private String setTypeSlug;

    public String getUid() {
        return uid;
    }

    public List<String> getScheduleUrls() {
        return scheduleUrls;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public String getTitle() {
        return title;
    }

    public String getSelf() {
        return self;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getSetTypeUrl() {
        return setTypeUrl;
    }

    public String getTempImage() {
        return tempImage;
    }

    public Long getFilmCount() {
        return filmCount;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public Object getHierarchyUrl() {
        return hierarchyUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public String getSlug() {
        return slug;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public String getLanguageEndsOn() {
        return languageEndsOn;
    }

    public String getCreated() {
        return created;
    }

    public List<Item> getItems() {
        if (items == null)
            items = new ArrayList<>();
        return items;
    }

    public String getModified() {
        return modified;
    }

    public String getSummary() {
        return summary;
    }

    public String getEndsOn() {
        return endsOn;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public String getSetTypeSlug() {
        return setTypeSlug;
    }
}
