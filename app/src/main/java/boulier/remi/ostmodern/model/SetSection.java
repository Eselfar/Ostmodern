package boulier.remi.ostmodern.model;

import java.util.List;

import boulier.remi.ostmodern.retrofit.ImageDetails;
import boulier.remi.ostmodern.retrofit.Set;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class SetSection {
    private String title;
    private String summary;
    private List<String> imageUrls;
    private ImageDetails imageDetails = null;

    public SetSection(Set set) {
        this.title = set.getTitle();
        this.summary = set.getSummary();
        this.imageUrls = set.getImageUrls();
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean asImageUrls() {
        return imageUrls != null && imageUrls.size() > 0;
    }

    public String getFirstImageUrl() {
        if (asImageUrls())
            return imageUrls.get(0);
        return "";
    }

    public ImageDetails getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(ImageDetails imageDetails) {
        this.imageDetails = imageDetails;
    }
}
