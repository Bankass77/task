package ml.taskmanager.action;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.services.TaskService;

@RequiredArgsConstructor
@AllArgsConstructor
public class AddTaskAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
	private TaskService taskService;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;

    @Override
    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();

        if (title == null || title.isEmpty()) {
            // Le champ titre est obligatoire, afficher un message d'erreur
            addActionError("Le champ titre est obligatoire");
            return INPUT;
            // navigation: redisplay the task form.
        } else if (dueDate == null) {
            addActionError("Le champ due date est obligatoire");
            return INPUT;
            // navigation: redisplay the task form.
        } else if (description == null || description.isEmpty()) {
            // Le champ description est obligatoire, afficher un message d'erreur
            addActionError("Le champ description est obligatoire");
            return INPUT;
            // navigation: redisplay the task form.
        } else {
            // Tous les champs sont valides, créer la tâche et l'enregistrer dans la base de données
            taskService.addTask(title, description, dueDate, completed);
            addActionMessage("Tâche ajoutée avec succès !");
            return SUCCESS;
        }
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
}
