package com.company.jmixpm.screen.employee;

import com.company.jmixpm.entity.EmployeeDetails;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.SaveContext;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@UiController("Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeEdit extends StandardEditor<Employee> {

//    @Autowired
//    private DataContext dataContext;
    @Autowired
    private Metadata metadata;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Employee> event) {
        EmployeeDetails employeeDetails = metadata.create(EmployeeDetails.class);
        event.getEntity().setEmployeeDetails(employeeDetails);
    }

    /*@Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        for (Object entity : saveContext.getEntitiesToSave()) {
            if (entity instanceof Employee) {
                EmployeeDetails employeeDetails = ((Employee) entity).getEmployeeDetails();
                employeeDetails = dataManager.save(employeeDetails);
                ((Employee) entity).setEmployeeDetails(employeeDetails);
            }
        }
        return dataManager.save(saveContext);
    }*/
}