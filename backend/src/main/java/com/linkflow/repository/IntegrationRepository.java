package com.linkflow.repository;

import com.linkflow.domain.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, UUID> {

    List<Integration> findByUser_Id(UUID id);

    Optional<Integration> findByIdAndUserId(UUID id, UUID userId);

    boolean existsByIdAndUserId(UUID id, UUID userId);
}
