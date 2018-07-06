package com.ibs.android.remotesm;

public class IBSItem {
    private String[] members;
    private String link;
    private String state;
    private boolean editable;
    private String type;
    private String name;
    private String label;
    private String category;
    private String[] tag;
    private String[] groupNames;

    public IBSItem(String[] members, String link, String state, boolean editable, String type, String name, String label, String category, String[] tag, String[] groupNames) {
        this.members = members;
        this.link = link;
        this.state = state;
        this.editable = editable;
        this.type = type;
        this.name = name;
        this.label = label;
        this.category = category;
        this.tag = tag;
        this.groupNames = groupNames;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public String[] getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String[] groupNames) {
        this.groupNames = groupNames;
    }
}
