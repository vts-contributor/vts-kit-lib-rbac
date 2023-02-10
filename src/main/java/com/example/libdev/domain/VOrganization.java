package com.example.libdev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VOrganization.
 */
@Entity
@Table(name = "v_organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VOrganization extends VAbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "full_path")
    private String fullPath;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "type")
    private Integer type;

    @ManyToMany
    @JoinTable(
        name = "rel_v_organization__v_user",
        joinColumns = @JoinColumn(name = "v_organization_id"),
        inverseJoinColumns = @JoinColumn(name = "v_user_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vRoles", "vOrganizations" }, allowSetters = true)
    private Set<VUser> vUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_v_organization__v_role",
        joinColumns = @JoinColumn(name = "v_organization_id"),
        inverseJoinColumns = @JoinColumn(name = "v_role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vOrganizations" }, allowSetters = true)
    private Set<VRole> vRoles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

//    public Long getId() {
//        return this.id;
//    }

    public VOrganization id(Long id) {
        this.setId(id);
        return this;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getParentId() {
        return this.parentId;
    }

    public VOrganization parentId(Long parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return this.code;
    }

    public VOrganization code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public VOrganization name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public VOrganization description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return this.path;
    }

    public VOrganization path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public VOrganization fullPath(String fullPath) {
        this.setFullPath(fullPath);
        return this;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public VOrganization enabled(Boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getType() {
        return this.type;
    }

    public VOrganization type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Set<VUser> getVUsers() {
        return this.vUsers;
    }

    public void setVUsers(Set<VUser> vUsers) {
        this.vUsers = vUsers;
    }

    public VOrganization vUsers(Set<VUser> vUsers) {
        this.setVUsers(vUsers);
        return this;
    }

    public VOrganization addVUser(VUser vUser) {
        this.vUsers.add(vUser);
        vUser.getVOrganizations().add(this);
        return this;
    }

    public VOrganization removeVUser(VUser vUser) {
        this.vUsers.remove(vUser);
        vUser.getVOrganizations().remove(this);
        return this;
    }

    public Set<VRole> getVRoles() {
        return this.vRoles;
    }

    public void setVRoles(Set<VRole> vRoles) {
        this.vRoles = vRoles;
    }

    public VOrganization vRoles(Set<VRole> vRoles) {
        this.setVRoles(vRoles);
        return this;
    }

    public VOrganization addVRole(VRole vRole) {
        this.vRoles.add(vRole);
        vRole.getVOrganizations().add(this);
        return this;
    }

    public VOrganization removeVRole(VRole vRole) {
        this.vRoles.remove(vRole);
        vRole.getVOrganizations().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof VOrganization)) {
//            return false;
//        }
//        return id != null && id.equals(((VOrganization) o).id);
//    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VOrganization{" +
            "id=" + getId() +
            ", parentId=" + getParentId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", path='" + getPath() + "'" +
            ", fullPath='" + getFullPath() + "'" +
            ", enabled='" + getEnabled() + "'" +
            ", type=" + getType() +
            "}";
    }
}
