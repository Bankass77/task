/*
 * package ml.taskmanager.action;
 * 
 * import java.time.LocalDateTime;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.apache.struts2.interceptor.ServletRequestAware; import
 * com.opensymphony.xwork2.ActionSupport; import lombok.RequiredArgsConstructor;
 * import ml.taskmanager.services.TaskService;
 * 
 * @RequiredArgsConstructor public class AddTaskAction extends ActionSupport
 * implements ServletRequestAware {
 * 
 * private final TaskService taskService; private AddTaskActionForm form;
 * private HttpServletRequest request;
 * 
 * public String execute() {
 * 
 * // Validate the form input if (hasErrors()) { return INPUT; }
 * 
 * // Get the form data String title = form.getTitle(); String description =
 * form.getDescription(); LocalDateTime duedate = form.getDueDate(); boolean
 * completed = form.isCompleted();
 * 
 * // Add the task taskService.addTask(title, description, duedate, completed);
 * 
 * // Redirect to the task list page return SUCCESS; }
 * 
 * @Override public void setServletRequest(HttpServletRequest request) {
 * this.request = request; }
 * 
 * public AddTaskActionForm getForm() { return form; }
 * 
 * public void setForm(AddTaskActionForm form) { this.form = form; }
 * 
 * private boolean hasErrors() { return getFieldErrors().size() > 0; }
 * 
 * }
 */