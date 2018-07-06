package com.ibs.android.remotesm;

public class Item {
    private String label;
    private String icon;
    private String link;

    public Item(String label, String icon, String link) {
        this.label = label;
        this.icon = icon;
        this.link=link;
    }

    public String getLabel() {
        return label;
    }


    public String getIcon() {
        return icon;
    }

    public String getLink() {

        return link;
    }
}
