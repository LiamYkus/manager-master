package com.manager.service;

import com.manager.DTO.ProjectConfirm;
import com.manager.DTO.WeeklyProject;
import com.manager.model.Project;
import com.manager.model.ProjectRegistration;
import com.manager.model.User;
import com.manager.repository.ProjectRegistrationRepository;
import com.manager.repository.ProjectRepository;
import com.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRegistrationRepository projectRegistrationRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAllByStatus();
    }

    public List<Project> getAllProjectsBy() {
        return projectRepository.findAllByStatusBy();
    }

    public Project getAllProjectsByStatus(Long id) {
        return projectRepository.findAllProjectByStatus(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project findProjectById(Long id){
        Project project = projectRepository.findProjectById(id);
        return project;
    }

    public void registerProject(Long projectId, Long studentId) {
        Optional<Project> project = projectRepository.findById(projectId);
        Optional<User> student = userRepository.findById(studentId);

        if (project.isPresent() && student.isPresent()) {
            ProjectRegistration registration = new ProjectRegistration();
            registration.setProject(project.get());
            registration.setStudent(student.get());
            registration.setStatus(ProjectRegistration.Status.ChoXacNhan);
            projectRegistrationRepository.save(registration);
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteByID(id);
    }

    public List<ProjectConfirm> getAllProjectsConfirm(Long id) {
        List<Object[]> results = projectRepository.findAllProjectConfirm(id);
        List<ProjectConfirm> projectConfirms = new ArrayList<>();

        for (Object[] result : results) {
            ProjectConfirm custom = new ProjectConfirm(
                    ((Number) result[0]).longValue(),
                    (String) result[1],
                    (String) result[2],
                    (Date) result[3],
                    (Date) result[4],
                    (String) result[5],
                    (String) result[6]
            );
            projectConfirms.add(custom);
        }
        return projectConfirms;
    }
}
