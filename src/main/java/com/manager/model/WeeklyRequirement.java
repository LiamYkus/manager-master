package com.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "WeeklyRequirements")
public class WeeklyRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requirementId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId",referencedColumnName = "projectId", nullable = false)
    private Project project;

    private String description;

    private Date startDate;

    private Date endDate;

}