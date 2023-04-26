package ml.taskmanager.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.junit.jupiter.api.Disabled;

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
    @Disabled
    public void testAddTask() throws SQLException {
        Task task = new Task();
        task.setTitle("Test task");
        task.setDescription("This task is a test task");
        task.setCompleted(false);
        task.setDueDate(LocalDateTime.now());
        taskDao.addTask(task);

        PreparedStatement ps = connection.prepareStatement("select * from tasks where id = ?");
        ps.setLong(1, task.getId());
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertEquals(task.getId(), rs.getLong("id"));
        assertEquals(task.getTitle(), rs.getString("title"));
        assertEquals(task.getDescription(), rs.getString("description"));
        assertEquals(task.isCompleted(), rs.getBoolean("completed"));
        assertEquals(task.getDueDate(), rs.getObject("due_date", LocalDateTime.class));
    }

    @Test
    @Disabled
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
        Task task = taskDao.getTaskById(3);
        task.setTitle("Test task");
        task.setDescription("This task is a test task");
        task.setTitle("Updated title");
        task.setDescription("Updated description");
      //  taskDao.addTask(task);

       
        taskDao.updateTask(task);

        PreparedStatement ps = connection.prepareStatement("select * from tasks where id = ?");
        ps.setLong(1, task.getId());
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertEquals(task.getId(), rs.getLong("id"));
        assertEquals(task.getTitle(), rs.getString("title"));
        assertEquals(task.getDescription(), rs.getString("description"));
        assertEquals(task.isCompleted(), rs.getBoolean("completed"));
        assertEquals(task.getDueDate(), rs.getObject("due_date", LocalDateTime.class));
    }

    @Test
    public void testGetAllTasks() throws SQLException {
		/*
		 * Task task1 = new Task(); task1.setTitle("Test task 1");
		 * task1.setDescription("This task is test task 1"); task1.setCompleted(false);
		 * task1.setDueDate(LocalDateTime.now()); taskDao.addTask(task1);
		 * 
		 * Task task2 = new Task(); task2.setTitle("Test task 2");
		 * task2.setCompleted(false); task2.setDueDate(LocalDateTime.now());
		 * task2.setDescription("This task is test task 2"); taskDao.addTask(task2);
		 */

        List<Task> tasks = taskDao.getAllTasks();

        assertNotNull(tasks.size());

		/*
		 * Task retrievedTask1 = tasks.get(0); Task retrievedTask2 = tasks.get(1);
		 */

		/*
		 * assertEquals(task1.getId(), retrievedTask1.getId());
		 * assertEquals(task1.getTitle(), retrievedTask1.getTitle());
		 * assertEquals(task1.getDescription(), retrievedTask1.getDescription());
		 * assertEquals(task1.getDueDate(), retrievedTask1.getDueDate());
		 * 
		 * 
		 * assertEquals(task2.getId(), retrievedTask2.getId());
		 * assertEquals(task2.getTitle(), retrievedTask2.getTitle());
		 * assertEquals(task2.getDescription(), retrievedTask2.getDescription());
		 * assertEquals(task2.getDueDate(), retrievedTask2.getDueDate());
		 */
        
        
    }
}
