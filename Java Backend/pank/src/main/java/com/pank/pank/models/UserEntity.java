package com.pank.pank.models;

import java.time.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    // ✅ Enums must be public
    public enum Role {
        user,
        admin
    }

    public enum Status {
        active,
        inactive
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "public_id")
    private String publicId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(columnDefinition = "TEXT")
    private String images;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    // ✅ JSON storage
    @Column(columnDefinition = "JSON")
    private String bio;

    @Column(name = "total_like")
    private Integer totalLike;

    @Column(name = "total_match")
    private Integer totalMatch;

    @Column(name = "user_preferance", columnDefinition = "JSON")
    private String userPreferance;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
