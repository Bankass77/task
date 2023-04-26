package ml.taskmanager.action;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.models.Task;
import ml.taskmanager.services.TaskService;

@RequiredArgsConstructor
@AllArgsConstructor
public class EditTaskAction extends Action {

	private TaskService taskService;

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String taskIdStr = request.getParameter("taskId");

		int taskId = Integer.parseInt(taskIdStr);
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		//String dateTime= request.getParameter("");
		//String is_done= request.getParameter("completed");
		Task task = taskService.getTaskById(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setCompleted(false);
		task.setDueDate(LocalDateTime.now());
		taskService.updateTask(taskId,LocalDateTime.now(), false , title, description);

		return "task-list.jsp";
	}

}
