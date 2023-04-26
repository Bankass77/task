package ml.taskmanager.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.models.Task;
import ml.taskmanager.services.TaskService;

@RequiredArgsConstructor
@AllArgsConstructor
public class TaskListAction extends Action {

	private TaskService taskService;

	private String execute(HttpServletRequest request, HttpServletResponse response) {

		List<Task> tasks = taskService.getAllTasks();
		request.setAttribute("tasks", tasks);
		return "task-list.jsp";

	}

}
