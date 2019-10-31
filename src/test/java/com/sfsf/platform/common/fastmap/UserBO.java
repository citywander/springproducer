package com.sfsf.platform.common.fastmap;

public class UserBO {
    
    private String id;
    
    private String name;
    
    private OrgBO org;
    
    public OrgBO getOrg() {
        return org;
    }

    public void setOrg(OrgBO org) {
        this.org = org;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
