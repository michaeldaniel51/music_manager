package danny.musicmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danny.musicmanager.enums.Emoji;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reaction")
public class Reaction {


    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private Emoji emoji;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Song song;

    @ManyToOne
    @JsonIgnore
    private Album album;
}
