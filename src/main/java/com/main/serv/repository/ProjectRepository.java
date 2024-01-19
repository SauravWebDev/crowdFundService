package com.main.serv.repository;

import com.main.serv.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Boolean existsByTitle(String title);
    Optional<Project> findByTitle(String title);
    @Query(
            value = "select * from project p order by p.created_at desc limit 100",
            nativeQuery = true
    )
    List<Project> getAllProjects(String orderBy);

    @Query(
            value = "select * from project p where owner = :ownerId order by p.created_at",
            nativeQuery = true
    )
    List<Project> getProjectsByOwnerId(String ownerId);

    Optional<Project> findById(int id);

}
