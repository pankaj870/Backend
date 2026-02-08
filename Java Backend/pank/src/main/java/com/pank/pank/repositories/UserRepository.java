package com.pank.pank.repositories;

import com.pank.pank.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByFirebaseUid(String firebaseUid);

    Optional<UserEntity> findByPublicId(String publicId);

    Optional<UserEntity> findByPhone(String phone);
}
