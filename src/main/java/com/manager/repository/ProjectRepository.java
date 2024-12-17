package com.manager.repository;

import com.manager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM project where project_id = ?1",nativeQuery = true)
    Project findProjectById(Long id);

    @Query(value = "DELETE FROM project where project_id = ?1",nativeQuery = true)
    Project deleteByID(Long id);

    @Query(value = "SELECT * FROM project WHERE status = N'Đang tiến hành' or status = N'Hủy';",nativeQuery = true)
    List<Project> findAllByStatus();

    @Query(value = "SELECT p.* \n" +
            "                   FROM project p \n" +
            "                   LEFT JOIN project_registrations pr ON p.project_id = pr.project_id\n" +
            "                   WHERE pr.project_id IS NULL",nativeQuery = true)
    List<Project> findAllByStatusBy();

    @Query(value = "SELECT * FROM project WHERE status = N'Đang tiến hành' or status = N'Hủy';",nativeQuery = true)
    List<Project> findAllPByStatus();

    @Query(value = "SELECT project.* FROM project LEFT JOIN project_registrations ON project.project_id = project_registrations.project_id WHERE project_registrations.student_id = ?1",nativeQuery = true)
    Project findAllProjectByStatus(Long id);

    @Query(value = "SELECT p.* FROM project_lecturers pl INNER JOIN user u ON pl.lecturer_id = u.user_id \n" +
            "INNER JOIN project p ON pl.project_id = p.project_id  \n" +
            "INNER JOIN project_registrations ON project_registrations.project_id = p.project_id\n" +
            "WHERE pl.lecturer_id = ?1 AND p.status LIKE '%DangTienHanh%'",nativeQuery = true)
    List<Project> findAllProject(Long id);

    @Query(value = "SELECT p.project_id, p.title, p.description, p.start_date, p.end_date, u.first_name, u.last_name FROM project_lecturers pl INNER JOIN user u ON pl.lecturer_id = u.user_id \n" +
            "INNER JOIN project p ON pl.project_id = p.project_id  \n" +
            "INNER JOIN project_registrations pr ON pr.project_id = p.project_id\n" +
            "WHERE pl.lecturer_id = ?1 AND pr.status LIKE '%ChoXacNhan%'",nativeQuery = true)
    List<Object[]> findAllProjectConfirm(Long id);

}
