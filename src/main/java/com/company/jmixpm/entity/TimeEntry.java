package com.company.jmixpm.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "TIME_ENTRY", indexes = {
        @Index(name = "IDX_TIME_ENTRY_TASK", columnList = "TASK_ID"),
        @Index(name = "IDX_TIME_ENTRY_USER", columnList = "USER_ID")
})
@Entity
public class TimeEntry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "TASK_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Task task;

    @Positive(message = "{msg://com.company.jmixpm.entity/TimeEntry.timeSpent.validation.Positive}")
    @NotNull
    @Column(name = "TIME_SPENT", nullable = false)
    private Integer timeSpent;

    @PastOrPresent
    @Column(name = "ENTRY_DATE")
    private LocalDateTime entryDate;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Length(message = "{msg://com.company.jmixpm.entity/TimeEntry.description.validation.Length}", min = 10)
    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}