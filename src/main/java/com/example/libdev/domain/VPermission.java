package com.example.libdev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VPermission.
 */
@Entity
@Table(name = "v_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VPermission extends VAbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "order")
    private Double order;

    @Column(name = "url")
    private String url;

    @Column(name = "component")
    private String component;

    @Column(name = "perms")
    private String perms;

    @ManyToMany
    @JoinTable(
            name="rel_v_role__v_permission",
            joinColumns = @JoinColumn(name="v_permission_id"),
            inverseJoinColumns = @JoinColumn(name="v_role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vOrganizations" }, allowSetters = true)
    private Set<VRole> vRoles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VPermission id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public VPermission parentId(Long parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return this.code;
    }

    public VPermission code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public VPermission name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public VPermission description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public VPermission type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getOrder() {
        return this.order;
    }

    public VPermission order(Double order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Double order) {
        this.order = order;
    }

    public String getUrl() {
        return this.url;
    }

    public VPermission url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return this.component;
    }

    public VPermission component(String component) {
        this.setComponent(component);
        return this;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerms() {
        return this.perms;
    }

    public VPermission perms(String perms) {
        this.setPerms(perms);
        return this;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Set<VRole> getvRoles() {
        return vRoles;
    }

    public void setvRoles(Set<VRole> vRoles) {
        this.vRoles = vRoles;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VPermission)) {
            return false;
        }
        return id != null && id.equals(((VPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VPermission{" +
            "id=" + getId() +
            ", parentId=" + getParentId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", order=" + getOrder() +
            ", url='" + getUrl() + "'" +
            ", component='" + getComponent() + "'" +
            ", perms='" + getPerms() + "'" +
            "}";
    }
}
