package com.joalvarez.springcontainer.data.repository.generals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface MultitenantRepository<ENT, PK> extends JpaRepository<ENT, PK> {
	List<ENT> findAllByUserId(UUID uuid);
	ENT findByIdAndUserId(PK id, UUID currentUserId);
}
