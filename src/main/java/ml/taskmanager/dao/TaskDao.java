package ml.taskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ml.taskmanager.models.Task;
import ml.taskmanager.utils.DatabaseUtils;


@Transactional
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

			PreparedStatement ps = connection.prepareStatement("insert into tasks (title, description) values(?,?)");
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
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

			PreparedStatement ps = connection.prepareStatement("update tasks set title=?, description=? where id=?");
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setLong(3, task.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<Task>();

		try {

			PreparedStatement ps = connection.prepareStatement("select * from tasks");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getLong("id"));
				task.setTitle(rs.getString("title"));
				task.setDescription(rs.getString("description"));

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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

}
