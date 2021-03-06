package danny.musicmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {


    @Id
    @GeneratedValue
    private int id;

    private String message;

    @ManyToOne
    @JsonIgnore
    private Song song;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Album album;

}
