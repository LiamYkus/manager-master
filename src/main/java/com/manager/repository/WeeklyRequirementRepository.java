package com.manager.repository;


import com.manager.model.WeeklyRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeeklyRequirementRepository extends JpaRepository<WeeklyRequirement, Long> {
    @Query(value = " SELECT weekly_requirements.*, project.title FROM weekly_requirements \n" +
            "                        INNER JOIN project ON weekly_requirements.project_id = project.project_id\n" +
            "                       INNER JOIN project_registrations ON project_registrations.project_id = project.project_id WHERE project_registrations.student_id = ?1 AND project.status = 'DangTienHanh'",nativeQuery = true)
    List<Object[]> findAllProject(Long id);

    @Query(value = " SELECT weekly_requirements.* FROM weekly_requirements \n" +
            "                                    INNER JOIN project ON weekly_requirements.project_id = project.project_id\n" +
            "                                    INNER JOIN project_lecturers ON project_lecturers.project_id = project.project_id \n" +
            "                                   INNER JOIN project_registrations ON project_registrations.project_id = project.project_id \n" +
            "                                   WHERE project_lecturers.lecturer_id = ?1 AND project.status = 'DangTienHanh'",nativeQuery = true)
    List<WeeklyRequirement> findAllProjectByUser(Long id);

}
