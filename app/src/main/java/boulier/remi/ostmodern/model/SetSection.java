package boulier.remi.ostmodern.model;

import boulier.remi.ostmodern.retrofit.Set;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class SetSection {
    private String title;
    private String summary;

    public SetSection(Set set) {
        this.title = set.getTitle();
        this.summary = set.getSummary();
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}
