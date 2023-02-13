package com.example.libdev.web.rest.dto;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MenuDTO implements Serializable {
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private String path;
    private String component;
    private Double order;
    private String type;

    private Set<MenuDTO> children;

    public MenuDTO(Long id, Long parentId, String code, String name, String path, String component, Double order, String type) {
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.name = name;
        this.path = path;
        this.component = component;
        this.order = order;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Double getOrder() {
        return order;
    }

    public void setOrder(Double order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<MenuDTO> children) {
        this.children = children;
    }

    public void addChild(MenuDTO child){
        if(this.children == null) this.children = new HashSet<>();
        this.children.add(child);
    }
}
