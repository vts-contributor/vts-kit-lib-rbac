package com.example.libdev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VEndpoint.
 */
@Entity
@Table(name = "v_endpoint")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VEndpoint extends VAbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @OneToMany(mappedBy = "vEndpoint")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vEndpoint" }, allowSetters = true)
    private Set<VRequestField> vRequestFields = new HashSet<>();

    @OneToMany(mappedBy = "vEndpoint")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vEndpoint" }, allowSetters = true)
    private Set<VResponseField> vResponseFields = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name="rel_v_role__v_endpoint",
            joinColumns = @JoinColumn(name="v_endpoint_id"),
            inverseJoinColumns = @JoinColumn(name="v_role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vOrganizations" }, allowSetters = true)
    private Set<VRole> vRoles = new HashSet<>();


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VEndpoint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public VEndpoint code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public VEndpoint name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public VEndpoint description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public VEndpoint url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return this.method;
    }

    public VEndpoint method(String method) {
        this.setMethod(method);
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<VRequestField> getVRequestFields() {
        return this.vRequestFields;
    }

    public void setVRequestFields(Set<VRequestField> vRequestFields) {
        if (this.vRequestFields != null) {
            this.vRequestFields.forEach(i -> i.setVEndpoint(null));
        }
        if (vRequestFields != null) {
            vRequestFields.forEach(i -> i.setVEndpoint(this));
        }
        this.vRequestFields = vRequestFields;
    }

    public VEndpoint vRequestFields(Set<VRequestField> vRequestFields) {
        this.setVRequestFields(vRequestFields);
        return this;
    }

    public VEndpoint addVRequestField(VRequestField vRequestField) {
        this.vRequestFields.add(vRequestField);
        vRequestField.setVEndpoint(this);
        return this;
    }

    public VEndpoint removeVRequestField(VRequestField vRequestField) {
        this.vRequestFields.remove(vRequestField);
        vRequestField.setVEndpoint(null);
        return this;
    }

    public Set<VResponseField> getVResponseFields() {
        return this.vResponseFields;
    }

    public void setVResponseFields(Set<VResponseField> vResponseFields) {
        if (this.vResponseFields != null) {
            this.vResponseFields.forEach(i -> i.setVEndpoint(null));
        }
        if (vResponseFields != null) {
            vResponseFields.forEach(i -> i.setVEndpoint(this));
        }
        this.vResponseFields = vResponseFields;
    }

    public VEndpoint vResponseFields(Set<VResponseField> vResponseFields) {
        this.setVResponseFields(vResponseFields);
        return this;
    }

    public VEndpoint addVResponseField(VResponseField vResponseField) {
        this.vResponseFields.add(vResponseField);
        vResponseField.setVEndpoint(this);
        return this;
    }

    public VEndpoint removeVResponseField(VResponseField vResponseField) {
        this.vResponseFields.remove(vResponseField);
        vResponseField.setVEndpoint(null);
        return this;
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
        if (!(o instanceof VEndpoint)) {
            return false;
        }
        return id != null && id.equals(((VEndpoint) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VEndpoint{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", url='" + getUrl() + "'" +
            ", method='" + getMethod() + "'" +
            "}";
    }
}
