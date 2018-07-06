package com.ibs.android.remotesm;

public class IBSLinkedPage {
    private String id;
    private String title;
    private String icon;
    private String link;
    private boolean leaf;
    private boolean timeout;

    public IBSLinkedPage(String id, String title, String icon, String link, boolean leaf, boolean timeout) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.link = link;
        this.leaf = leaf;
        this.timeout = timeout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }
}
