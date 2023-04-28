package ml.taskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import javax.transaction.Transactional;

import ml.taskmanager.models.Task;
import ml.taskmanager.utils.DatabaseUtils;


//@Transactional
public class TaskDao {

	private Connection connection;

	public TaskDao() {

		try {
			connection = DatabaseUtils.getConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @param task
	 */
	public void addTask(Task task) {

		try {

			PreparedStatement ps = connection.prepareStatement("insert into tasks (title, description, due_date, completed) values(?,?,?,?)");
			//ps.setLong(1, task.getId());
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setObject(3, java.sql.Timestamp.valueOf(task.getDueDate()));
			ps.setBoolean(4, task.isCompleted());
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param taskId
	 */
	public void deleteTask(int taskId) {

		try {
			PreparedStatement ps = connection.prepareStatement("delete from tasks where id=?");
			ps.setInt(1, taskId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	/**
	 * @param task
	 */
	public void updateTask(Task task) {

		try {

			PreparedStatement ps = connection.prepareStatement("update tasks set title=?, description=? , due_date=?, completed=?, where id=?");
			
			ps.setLong(1, task.getId());
			ps.setString(2, task.getTitle());
			ps.setString(3, task.getDescription());
			ps.setObject(4, java.sql.Timestamp.valueOf(task.getDueDate()));
			ps.setBoolean(5, task.isCompleted());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<Task>();

		try {

			PreparedStatement ps = connection.prepareStatement("SELECT id, title, description, DATE_FORMAT(due_date, '%d-%m-%Y') AS due_date_formatted, completed FROM  tasks;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getLong("id"));
				task.setTitle(rs.getString("title"));
				task.setDescription(rs.getString("description"));
				task.setCompleted(rs.getBoolean("completed"));
				//task.setDueDate(LocalDateTime.parse(rs.getString("due_date")));
				tasks.add(task);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public Task getTaskById(int taskId) {

		Task task = new Task();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from tasks where id=?");
			ps.setInt(1, taskId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				task.setId(rs.getLong("id"));
				task.setDescription(rs.getString("description"));
				task.setTitle(rs.getString("title"));
				task.setCompleted(rs.getBoolean("completed"));
				task.setDueDate(LocalDateTime.parse(rs.getString("due_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

}
