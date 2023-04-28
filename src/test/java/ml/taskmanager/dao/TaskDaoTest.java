package ml.taskmanager.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import ml.taskmanager.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ml.taskmanager.utils.DatabaseUtils;

public class TaskDaoTest {

	private Connection connection;
	private TaskDao taskDao;

	@BeforeEach
	public void setUp() {
		try {
			connection = DatabaseUtils.getConnection();
			taskDao = new TaskDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	// @Disabled
	public void testAddTask() throws SQLException {
		Task task = new Task();
		task.setId(38l);
		task.setTitle("Test task" + System.currentTimeMillis());
		task.setDescription("This task is a test task");
		task.setCompleted(false);
		task.setDueDate(LocalDateTime.now());
		taskDao.addTask(task);
		LocalDateTime dueDate = LocalDateTime.parse("2023-04-28T12:31:07.972889655");
		java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dueDate);
  String query="insert into tasks.tasks (title, description, due_date, completed) values(?,?,?,?)";
		PreparedStatement ps = connection
				.prepareStatement(query);
		//ps.setInt(1, task.getId().intValue());
		ps.setString(1, task.getTitle());
		ps.setString(2, task.getDescription());
		ps.setTimestamp(3, timestamp);
		ps.setBoolean(4, task.isCompleted());
		int rowsAffected = ps.executeUpdate();
		assertTrue(rowsAffected == 1);

	

	}

	@Test
	//@Disabled
	public void testDeleteTask() throws SQLException {

		Task task = taskDao.getTaskById(8);
		taskDao.deleteTask(task.getId().intValue());

		PreparedStatement ps = connection.prepareStatement("select * from tasks where id = ?");
		ps.setLong(1, task.getId());
		ResultSet rs = ps.executeQuery();
		assertFalse(rs.next());

	}

	@Test
	//@Disabled
	public void testUpdateTask() throws SQLException {
		Task task = taskDao.getTaskById(9);
		task.setTitle("Test task");
		task.setDescription("Lorem ipsum dolor sit amet.");
		task.setTitle("Updated title");
		task.setDescription("Updated description");
		// taskDao.addTask(task);

		taskDao.updateTask(task);

		PreparedStatement ps = connection.prepareStatement("select * from tasks where id = ?");
		ps.setLong(1, task.getId());
		ps.executeUpdate();
		ResultSet rs = ps.getResultSet();
		assertTrue(rs.next());
		assertEquals(task.getId(), rs.getLong("id"));
		assertEquals(task.getTitle(), rs.getString("title"));
		assertEquals(task.getDescription(), rs.getString("description"));
		assertEquals(task.isCompleted(), rs.getBoolean("completed"));
		assertEquals(task.getDueDate(), rs.getObject("due_date", LocalDateTime.class));
	}

	@Test
	public void testGetAllTasks() throws SQLException {
	
		List<Task> tasks = taskDao.getAllTasks();

		assertNotNull(tasks.size());

		

	}
}
