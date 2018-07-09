package com.ibs.android.remotesm;

public class Item {
    private String label;
    private String icon;
    private String link;
    private String type;
    private String state;

    public Item(String label, String icon, String link, String type,String state) {
        this.label = label;
        this.icon = icon;
        this.link=link;
        this.type=type;
        this.state=state;
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

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }
}
