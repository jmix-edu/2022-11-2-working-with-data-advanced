package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Validated(value = {Default.class, UiComponentChecks.class, UiCrossFieldChecks.class})
@Service
public class ProjectsService {
    @Autowired
    private DataManager dataManager;

    public void saveProject(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}
