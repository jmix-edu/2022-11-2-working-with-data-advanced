package com.company.jmixpm.screen.project;

import com.company.jmixpm.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.Id;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(InitEvent event) {
        Task one = dataManager.load(Id.of(UUID.fromString("842335f3-7b91-a088-6921-0a292635dbfb"), Task.class)).one();

        Project project = one.getProject();

        project.getName();

        Task one1 = dataManager.load(Task.class).all().list().get(1);

        Project project1 = one1.getProject();

        project1.getName();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Integer newProjectsCount = dataManager.loadValue("select count(e) from Project e " +
                                "where :session_isManager = true " +
                                "and e.projectStatus = @enum(com.company.jmixpm.entity.ProjectStatus.NEW) " +
                                "and e.manager.id = :current_user_id",
                        Integer.class)
                .one();

        if (newProjectsCount > 0) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("New projects")
                    .withDescription("You have projects with NEW status: " + newProjectsCount)
                    .show();
        }
    }
}