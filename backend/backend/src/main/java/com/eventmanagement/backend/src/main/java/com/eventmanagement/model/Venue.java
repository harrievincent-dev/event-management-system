package com.eventmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Venue name is required")
    @Size(min = 2, max = 100, message = "Venue name must be between 2 and 100 characters")
    @Column(name = "venue_name")
    private String venueName;
    
    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;
    
    @NotBlank(message = "City is required")
    private String city;
    
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    private String country;
    
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "contact_person")
    private String contactPerson;
    
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Email(message = "Email should be valid")
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(name = "rental_cost", precision = 10, scale = 2)
    private Double rentalCost;
    
    @Column(name = "amenities", length = 1000)
    private String amenities;
    
    @Column(name = "parking_available")
    private Boolean parkingAvailable;
    
    @Column(name = "wifi_available")
    private Boolean wifiAvailable;
    
    @Column(name = "catering_available")
    private Boolean cateringAvailable;
    
    @Column(name = "av_equipment_available")
    private Boolean avEquipmentAvailable;
    
    @Enumerated(EnumType.STRING)
    private VenueStatus status;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
    
    public enum VenueStatus {
        ACTIVE, INACTIVE, MAINTENANCE, UNAVAILABLE
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = VenueStatus.ACTIVE;
        }
        if (parkingAvailable == null) {
            parkingAvailable = false;
        }
        if (wifiAvailable == null) {
            wifiAvailable = false;
        }
        if (cateringAvailable == null) {
            cateringAvailable = false;
        }
        if (avEquipmentAvailable == null) {
            avEquipmentAvailable = false;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Venue() {}
    
    public Venue(String venueName, String address, String city, Integer capacity) {
        this.venueName = venueName;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    
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
    
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public Double getRentalCost() { return rentalCost; }
    public void setRentalCost(Double rentalCost) { this.rentalCost = rentalCost; }
    
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    
    public Boolean getParkingAvailable() { return parkingAvailable; }
    public void setParkingAvailable(Boolean parkingAvailable) { this.parkingAvailable = parkingAvailable; }
    
    public Boolean getWifiAvailable() { return wifiAvailable; }
    public void setWifiAvailable(Boolean wifiAvailable) { this.wifiAvailable = wifiAvailable; }
    
    public Boolean getCateringAvailable() { return cateringAvailable; }
    public void setCateringAvailable(Boolean cateringAvailable) { this.cateringAvailable = cateringAvailable; }
    
    public Boolean getAvEquipmentAvailable() { return avEquipmentAvailable; }
    public void setAvEquipmentAvailable(Boolean avEquipmentAvailable) { this.avEquipmentAvailable = avEquipmentAvailable; }
    
    public VenueStatus getStatus() { return status; }
    public void setStatus(VenueStatus status) { this.status = status; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
    
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (postalCode != null) fullAddress.append(" ").append(postalCode);
        if (country != null) fullAddress.append(", ").append(country);
        return fullAddress.toString();
    }
}
