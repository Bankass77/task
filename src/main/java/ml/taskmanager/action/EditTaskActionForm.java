/*
 * package ml.taskmanager.action;
 * 
 * import java.time.LocalDateTime;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.apache.struts.action.ActionErrors; import
 * org.apache.struts.action.ActionForm; import
 * org.apache.struts.action.ActionMapping; import
 * org.apache.struts.action.ActionMessage;
 * 
 * import lombok.Data; import lombok.Getter; import lombok.Setter;
 * 
 * @Data
 * 
 * @Getter
 * 
 * @Setter public class EditTaskActionForm extends ActionForm { private static
 * final long serialVersionUID = 1L; private LocalDateTime dueDate; private
 * boolean completed; private String title; private String description;
 * 
 *//**
	 * Validate the user input. Called automatically by Struts framework.
	 *//*
		 * public ActionErrors validate(ActionMapping mapping, HttpServletRequest
		 * request) {
		 * 
		 * // create a blank ActionErrors ActionErrors errors = new ActionErrors();
		 * 
		 * // Check User input enter if (dueDate == null) {
		 * 
		 * errors.add("duedate", new ActionMessage("task.error.duedate.missing")); }
		 * else if (title.isBlank() || title.isEmpty()) { errors.add("title", new
		 * ActionMessage("task.error.title.missing")); } else if (description.isBlank()
		 * || description.isEmpty()) {
		 * 
		 * errors.add("description", new
		 * ActionMessage("task.error.description.missing")); }
		 * 
		 * return errors; }
		 * 
		 * }
		 */