package com.company.jmixpm.entity;

import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.validation.ProjectLabelsSize;
import com.company.jmixpm.validation.ValidDateProject;
import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.PropertyDatatype;
import io.jmix.core.pessimisticlocking.PessimisticLock;
import io.jmix.core.validation.group.UiCrossFieldChecks;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@PessimisticLock(timeoutSec = 10)
@ValidDateProject(groups = UiCrossFieldChecks.class)
@JmixEntity
@Table(name = "PROJECT", indexes = {
        @Index(name = "IDX_PROJECT_MANAGER", columnList = "MANAGER_ID"),
        @Index(name = "IDX_PROJECT_UNQ_NAME", columnList = "NAME", unique = true)
})
@Entity
public class Project {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TOTAL_ESTIMATED_EFFORTS")
    private Integer totalEstimatedEfforts;

    @Column(name = "PROJECT_STATUS")
    private Integer projectStatus;

    @NotNull
    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @NotNull
    @JoinColumn(name = "MANAGER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User manager;

    @JoinTable(name = "PROJECT_USER_LINK",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> participants;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    //    @Convert(converter = ProjectLabelsConverter.class)
    @ProjectLabelsSize(min = 2, max = 5)
    @PropertyDatatype("projectLabels")
    @Column(name = "PROJECT_LABELS")
    private ProjectLabels projectLabels;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public Integer getTotalEstimatedEfforts() {
        return totalEstimatedEfforts;
    }

    public void setTotalEstimatedEfforts(Integer totalEstimatedEfforts) {
        this.totalEstimatedEfforts = totalEstimatedEfforts;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public ProjectLabels getProjectLabels() {
        return projectLabels;
    }

    public void setProjectLabels(ProjectLabels projectLabels) {
        this.projectLabels = projectLabels;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus == null ? null : ProjectStatus.fromId(projectStatus);
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.getId();
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}