package danny.musicmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class FeedBack {


    @Id
    @GeneratedValue
    private int id;


    private String message;

    @ManyToOne
    private User user;
}
