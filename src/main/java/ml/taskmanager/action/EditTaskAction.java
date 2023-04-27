package ml.taskmanager.action;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletResponseAware;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ActionSupport;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.services.TaskService;

@Namespace("/")
@Results({
    @Result(name = "input", location = "/edit-task.jsp"),
    @Result(name = "success", location = "/task-list.action", type = "redirect")
})
@AllArgsConstructor
@RequiredArgsConstructor
public class EditTaskAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private TaskService taskService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private int taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Action("edit-task")
    public String execute() throws Exception {

        if (title == null || title.isEmpty()) {
            // Le champ titre est obligatoire, afficher un message d'erreur
            addActionError("Title is required");
            return "input";
            // navigation: redisplay the task form.
        } else if (dueDate == null) {
            addActionError("Due date is required");
            return "input";
            // navigation: redisplay the task form.
        } else if (description == null || description.isEmpty()) {
            // Le champ description est obligatoire, afficher un message d'erreur
            addActionError("Description is required");
            return "input";
            // navigation: redisplay the task form.
        } else {
            // Tous les champs sont valides, créer la tâche et l'enregistrer dans la base de données
            taskService.updateTask(taskId, dueDate, completed, title, description);
            return "success";
        }
    }

    public void validate() {
        if (!ServletActionContext.getRequest().getMethod().equalsIgnoreCase("POST")) {
            return;
        }
        if (title == null || title.isEmpty()) {
            // Le champ titre est obligatoire, afficher un message d'erreur
            addActionError("Title is required");
        }
        if (dueDate == null) {
            addActionError("Due date is required");
        }
        if (description == null || description.isEmpty()) {
            // Le champ description est obligatoire, afficher un message d'erreur
            addActionError("Description is required");
        }
    }

	@Override
	public void withServletResponse(HttpServletResponse response) {
	
		
	}
}
