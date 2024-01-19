package com.main.serv.repository;

import com.main.serv.entity.Donation;
import com.main.serv.entity.DonationSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query(
            value = "select sum(d.amount) as sum from donation d where project_id = :projectId",
            nativeQuery = true
    )
    Integer getProjectByTotalAmount(@Param("projectId") Integer projectId);

    @Query(
            value = "select count(d.id) as count from donation d where project_id = :projectId",
            nativeQuery = true
    )
    Integer getDonorsByProjectId(@Param("projectId") Integer projectId);
}
