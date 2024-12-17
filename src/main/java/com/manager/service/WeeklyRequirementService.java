package com.manager.service;
import com.manager.DTO.LecturerProjectCustom;
import com.manager.DTO.WeeklyProject;
import com.manager.model.WeeklyRequirement;
import com.manager.repository.WeeklyRequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeeklyRequirementService {
    @Autowired
    private WeeklyRequirementRepository weeklyRequirementRepository;

    public List<WeeklyRequirement> getAllWeeklyRequirements(Long id) {
        return weeklyRequirementRepository.findAllProjectByUser(id);
    }

    public List<WeeklyProject> getAllWeeklyProject(Long id) {
        List<Object[]> results = weeklyRequirementRepository.findAllProject(id);
        List<WeeklyProject> weeklyProjects = new ArrayList<>();

        for (Object[] result : results) {
            WeeklyProject custom = new WeeklyProject(
                    ((Number) result[0]).longValue(),
                    (String) result[1],
                    (Date) result[2],
                    (Date) result[3],
                    ((Number) result[4]).longValue(),
                    (String) result[5]
            );
            weeklyProjects.add(custom);
        }
        return weeklyProjects;
    }

    public WeeklyRequirement saveWeeklyRequirement(WeeklyRequirement weeklyRequirement) {
        return weeklyRequirementRepository.save(weeklyRequirement);
    }

    public Optional<WeeklyRequirement> getWeeklyRequirementById(Long id) {
        return weeklyRequirementRepository.findById(id);
    }

    public void deleteWeeklyRequirement(Long id) {
        weeklyRequirementRepository.deleteById(id);
    }
}
