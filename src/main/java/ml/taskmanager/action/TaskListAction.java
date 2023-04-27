package ml.taskmanager.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.models.Task;
import ml.taskmanager.services.TaskService;

@RequiredArgsConstructor
@AllArgsConstructor
public class TaskListAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
	private TaskService taskService;

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String execute() {
        List<Task> tasks = taskService.getAllTasks();
        addActionMessage("Task list retrieved successfully.");
        ServletActionContext.getRequest().setAttribute("tasks", tasks);
        return SUCCESS;
    }
}

