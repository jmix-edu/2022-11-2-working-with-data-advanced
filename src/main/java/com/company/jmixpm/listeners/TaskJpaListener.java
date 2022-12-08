package com.company.jmixpm.listeners;

import com.company.jmixpm.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class TaskJpaListener {

    private final static Logger log = LoggerFactory.getLogger(TaskJpaListener.class);

    @PreUpdate
    @PreRemove
    @PrePersist
    public void beforeUpdate(Task task) {
        log.info(task.getClass().getSimpleName() + " before update: " + task.getId());
    }

    @PostUpdate
    @PostRemove
    @PostPersist
    public void afterUpdate(Task task) {
        log.info(task.getClass().getSimpleName() + " after update: " + task.getId());
    }
}
