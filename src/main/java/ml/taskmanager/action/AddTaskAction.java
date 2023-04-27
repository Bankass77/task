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
public class TaskAction extends ActionSupport {

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















    private Task task;
    // getters and setters for task property
    
    






    
    public String execute() throws Exception {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM tasks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, task.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date"));
            }
        }
        return SUCCESS;
    }
    
    public String create() throws Exception {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO tasks (name, description, due_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            stmt.executeUpdate();
        }
        return SUCCESS;
    }
    
    public String update() throws Exception {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE tasks SET name = ?, description = ?, due_date = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setInt(4, task.getId());
            stmt.executeUpdate();
        }
        return SUCCESS;
    }
    
    public String delete() throws Exception {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        }
        return SUCCESS;
    }
    
    public List<Task> getTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM tasks";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date"));
                tasks.add(task);
            }
        }
        return tasks;
    }
}

}
