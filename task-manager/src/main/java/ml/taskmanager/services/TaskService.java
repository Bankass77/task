package ml.taskmanager.services;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import ml.taskmanager.dao.TaskDao;
import ml.taskmanager.models.Task;

@RequiredArgsConstructor
public class TaskService {

	private TaskDao taskDao;

	public void addTask(String title, String description) {
		Task task = new Task(title, description);
		taskDao.addTask(task);
	}

	public void deleteTask(int taskId) {
		taskDao.deleteTask(taskId);
	}

	public void updateTask(int taskId, LocalDateTime localDateTime,boolean completed,String title, String description) {
		Task task = new Task((long) taskId, localDateTime, completed, title, description);
		taskDao.updateTask(task);
	}

	public List<Task> getAllTasks() {

		return taskDao.getAllTasks();
	}

	public Task getTaskById(int taskId) {

		return taskDao.getTaskById(taskId);
	}
}