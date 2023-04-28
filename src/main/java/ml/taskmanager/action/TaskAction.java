package ml.taskmanager.action;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import lombok.RequiredArgsConstructor;
import ml.taskmanager.models.Task;
import ml.taskmanager.services.TaskService;

@Namespace("/")
@Results({ @Result(name = "input", location = "/edit-task.jsp"),
		@Result(name = "success", location = "/task-list.action", type = "redirect") })
@RequiredArgsConstructor

public class TaskAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final TaskService taskService = new TaskService();
	private String title;
	private String description;
	private LocalDateTime dueDate;
	private boolean completed;

	@Override
	public String execute() throws Exception {

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
			// Tous les champs sont valides, créer la tâche et l'enregistrer dans la base de
			// données
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

	@Action("add-task")
	public String createTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// Retrieve form data from request parameters
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		String dateString = request.getParameter("due_date");
		LocalDateTime dueDate = null;
		if (dateString != null && !dateString.isEmpty()) {
		   dueDate = LocalDateTime.parse(dateString);
		}
		//LocalDateTime duedate = LocalDateTime.parse(request.getParameter("due_date"));
		boolean isDone = Boolean.parseBoolean(request.getParameter("complteted"));
		// Create a new task model object
		Task task = new Task(title, description);
		task.setCompleted(isDone);
		task.setDescription(description);
		task.setDueDate(dueDate);
		task.setTitle(title);
		// Save the task to the database or in-memory data structure
		taskService.addTask(task.getTitle(), task.getDescription(), task.getDueDate(), task.isCompleted());

		// Return a success message to the view
		return SUCCESS;
	}

	@Action("edit-task")
	public String updateTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// Retrieve form data from request parameters
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		// Retrieve the task from the database or in-memory data structure
		Task task = taskService.getTaskById(taskId);
		// Update the task object with the new data
		task.setTitle(title);
		task.setDescription(description);
		task.setDueDate(LocalDateTime.parse(request.getParameter("due_date")));
		task.setCompleted(Boolean.parseBoolean(request.getParameter("completed")));

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
			// Save the updated task to the database or in-memory data structure
			taskService.addTask(task.getTitle(), task.getDescription(), task.getDueDate(), task.isCompleted());
			// Return a success message to the view
			return SUCCESS;
		}

	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// Retrieve form data from request parameters
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		taskService.deleteTask(taskId);

		return SUCCESS;
	}

	@Action("task-list")
	public String listTasks() {
		List<Task> tasks = taskService.getAllTasks();
		addActionMessage("Task list retrieved successfully.");
		ServletActionContext.getRequest().setAttribute("tasks", tasks);
		return SUCCESS;
	}
}
