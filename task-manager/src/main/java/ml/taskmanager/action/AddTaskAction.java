package ml.taskmanager.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ml.taskmanager.services.TaskService;

@RequiredArgsConstructor
@AllArgsConstructor
public class AddTaskAction  extends Action {
	
	private TaskService taskService;
	
	
	public String exedcute(HttpServletRequest request, HttpServletResponse response) {
		
		String title= request.getParameter("title");
		String description= request.getParameter("description");
		
		taskService.addTask(title, description);
		return "task-list.jsp";
	}
	

}
