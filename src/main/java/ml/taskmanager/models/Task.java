package ml.taskmanager.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tasks")
@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Column
	private LocalDateTime dueDate;

	@Column(name = "completed")
	private boolean completed;

	// Constructor
	public Task(String title, String description) {
		this.title = title;
		this.description = description;
	}

	// Constructor
	public Task(String title, String description, LocalDateTime dueDate, boolean completed) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
	}

}
