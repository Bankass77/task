package ml.taskmanager.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "tasks")
@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDateTime dueDate;
	
	@Column
	private boolean completed;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;
	
	   // Constructor
	public Task(String title, String description) {
		this.title=title;
		this.description=description;
	}
	
	   // Constructor
    public Task(String title, String description, LocalDateTime dueDate, boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
