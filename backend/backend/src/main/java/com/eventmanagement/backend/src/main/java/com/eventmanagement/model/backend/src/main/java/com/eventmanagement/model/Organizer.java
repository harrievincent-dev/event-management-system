package com.eventmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "organizers")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Organization name is required")
    @Size(min = 2, max = 100, message = "Organization name must be between 2 and 100 characters")
    @Column(name = "organization_name")
    private String organizationName;
    
    @NotBlank(message = "Contact person name is required")
    @Column(name = "contact_person")
    private String contactPerson;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;
    
    private String city;
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    private String country;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "website_url")
    private String websiteUrl;
    
    @Column(name = "social_media_links", length = 500)
    private String socialMediaLinks;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type")
    private OrganizationType organizationType;
    
    @Column(name = "registration_number")
    private String registrationNumber;
    
    @Enumerated(EnumType.STRING)
    private OrganizerStatus status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
    
    public enum OrganizationType {
        CORPORATE, NON_PROFIT, INDIVIDUAL, GOVERNMENT, EDUCATIONAL
    }
    
    public enum OrganizerStatus {
        ACTIVE, INACTIVE, SUSPENDED, PENDING_VERIFICATION
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = OrganizerStatus.PENDING_VERIFICATION;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Organizer() {}
    
    public Organizer(String organizationName, String contactPerson, String email, String phoneNumber, String address) {
        this.organizationName = organizationName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
    
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
    
    public String getSocialMediaLinks() { return socialMediaLinks; }
    public void setSocialMediaLinks(String socialMediaLinks) { this.socialMediaLinks = socialMediaLinks; }
    
    public OrganizationType getOrganizationType() { return organizationType; }
    public void setOrganizationType(OrganizationType organizationType) { this.organizationType = organizationType; }
    
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    
    public OrganizerStatus getStatus() { return status; }
    public void setStatus(OrganizerStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
