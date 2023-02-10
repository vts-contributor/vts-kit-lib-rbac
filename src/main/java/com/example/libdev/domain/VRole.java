package com.example.libdev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VRole.
 */
@Entity
@Table(name = "v_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VRole extends VAbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "application_id")
    private Long applicationId;

    @ManyToMany(mappedBy = "vRoles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vRoles", "vOrganizations" }, allowSetters = true)
    private Set<VUser> vUsers = new HashSet<>();

    @ManyToMany(mappedBy = "vRoles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vRoles" }, allowSetters = true)
    private Set<VOrganization> vOrganizations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

//    public Long getId() {
//        return this.id;
//    }

    public VRole id(Long id) {
        this.setId(id);
        return this;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getCode() {
        return this.code;
    }

    public VRole code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public VRole name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public VRole description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getApplicationId() {
        return this.applicationId;
    }

    public VRole applicationId(Long applicationId) {
        this.setApplicationId(applicationId);
        return this;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Set<VUser> getVUsers() {
        return this.vUsers;
    }

    public void setVUsers(Set<VUser> vUsers) {
        if (this.vUsers != null) {
            this.vUsers.forEach(i -> i.removeVRole(this));
        }
        if (vUsers != null) {
            vUsers.forEach(i -> i.addVRole(this));
        }
        this.vUsers = vUsers;
    }

    public VRole vUsers(Set<VUser> vUsers) {
        this.setVUsers(vUsers);
        return this;
    }

    public VRole addVUser(VUser vUser) {
        this.vUsers.add(vUser);
        vUser.getVRoles().add(this);
        return this;
    }

    public VRole removeVUser(VUser vUser) {
        this.vUsers.remove(vUser);
        vUser.getVRoles().remove(this);
        return this;
    }

    public Set<VOrganization> getVOrganizations() {
        return this.vOrganizations;
    }

    public void setVOrganizations(Set<VOrganization> vOrganizations) {
        if (this.vOrganizations != null) {
            this.vOrganizations.forEach(i -> i.removeVRole(this));
        }
        if (vOrganizations != null) {
            vOrganizations.forEach(i -> i.addVRole(this));
        }
        this.vOrganizations = vOrganizations;
    }

    public VRole vOrganizations(Set<VOrganization> vOrganizations) {
        this.setVOrganizations(vOrganizations);
        return this;
    }

    public VRole addVOrganization(VOrganization vOrganization) {
        this.vOrganizations.add(vOrganization);
        vOrganization.getVRoles().add(this);
        return this;
    }

    public VRole removeVOrganization(VOrganization vOrganization) {
        this.vOrganizations.remove(vOrganization);
        vOrganization.getVRoles().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof VRole)) {
//            return false;
//        }
//        return id != null && id.equals(((VRole) o).id);
//    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VRole{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", applicationId=" + getApplicationId() +
            "}";
    }
}
