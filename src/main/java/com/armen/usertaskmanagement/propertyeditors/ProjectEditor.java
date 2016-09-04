package com.armen.usertaskmanagement.propertyeditors;

import com.armen.usertaskmanagement.model.Project;
import com.armen.usertaskmanagement.model.Task;
import com.armen.usertaskmanagement.service.ProjectService;
import com.armen.usertaskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.beans.PropertyEditorSupport;

/**
 * Created by armennalbandyan on 29/03/2016.
 */
public class ProjectEditor extends PropertyEditorSupport {

    @Autowired
    @Qualifier(value="projectService")
    private ProjectService projectService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Converts given text id of project to Project
     * @param text
     */
    @Override
    public void setAsText(String text) {
        Project project = this.projectService.getProjectById(Integer.parseInt(text));
        this.setValue(project);
    }

    /**
     * Converts given project into string "id" where id is project id
     * @return
     */
    @Override
    public String getAsText() {
        Project project = null;
        if(this.getValue() != null){
            project = (Project) this.getValue();
            return "" + project.getId();
        }
        return null;
    }
}
