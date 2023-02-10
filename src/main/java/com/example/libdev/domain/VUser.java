package com.example.libdev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VUser.
 */
@Entity
@Table(name = "v_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VUser extends VAbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private String emailVerified;

    @Column(name = "language")
    private String language;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
        name = "rel_v_user__v_role",
        joinColumns = @JoinColumn(name = "v_user_id"),
        inverseJoinColumns = @JoinColumn(name = "v_role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vOrganizations" }, allowSetters = true)
    private Set<VRole> vRoles = new HashSet<>();

    @ManyToMany(mappedBy = "vUsers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "vUsers", "vRoles" }, allowSetters = true)
    private Set<VOrganization> vOrganizations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

//    public Long getId() {
//        return this.id;
//    }

    public VUser id(Long id) {
        this.setId(id);
        return this;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUsername() {
        return this.username;
    }

    public VUser username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public VUser fullname(String fullname) {
        this.setFullname(fullname);
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public VUser firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public VUser lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public VUser password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public VUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerified() {
        return this.emailVerified;
    }

    public VUser emailVerified(String emailVerified) {
        this.setEmailVerified(emailVerified);
        return this;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getLanguage() {
        return this.language;
    }

    public VUser language(String language) {
        this.setLanguage(language);
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public VUser enabled(Boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<VRole> getVRoles() {
        return this.vRoles;
    }

    public void setVRoles(Set<VRole> vRoles) {
        this.vRoles = vRoles;
    }

    public VUser vRoles(Set<VRole> vRoles) {
        this.setVRoles(vRoles);
        return this;
    }

    public VUser addVRole(VRole vRole) {
        this.vRoles.add(vRole);
        vRole.getVUsers().add(this);
        return this;
    }

    public VUser removeVRole(VRole vRole) {
        this.vRoles.remove(vRole);
        vRole.getVUsers().remove(this);
        return this;
    }

    public Set<VOrganization> getVOrganizations() {
        return this.vOrganizations;
    }

    public void setVOrganizations(Set<VOrganization> vOrganizations) {
        if (this.vOrganizations != null) {
            this.vOrganizations.forEach(i -> i.removeVUser(this));
        }
        if (vOrganizations != null) {
            vOrganizations.forEach(i -> i.addVUser(this));
        }
        this.vOrganizations = vOrganizations;
    }

    public VUser vOrganizations(Set<VOrganization> vOrganizations) {
        this.setVOrganizations(vOrganizations);
        return this;
    }

    public VUser addVOrganization(VOrganization vOrganization) {
        this.vOrganizations.add(vOrganization);
        vOrganization.getVUsers().add(this);
        return this;
    }

    public VUser removeVOrganization(VOrganization vOrganization) {
        this.vOrganizations.remove(vOrganization);
        vOrganization.getVUsers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof VUser)) {
//            return false;
//        }
//        return id != null && id.equals(((VUser) o).id);
//    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VUser{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", emailVerified='" + getEmailVerified() + "'" +
            ", language='" + getLanguage() + "'" +
            ", enabled='" + getEnabled() + "'" +
            "}";
    }
}
