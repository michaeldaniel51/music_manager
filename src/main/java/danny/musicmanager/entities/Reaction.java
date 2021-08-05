package danny.musicmanager.entities;

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
    private User user;

    @ManyToOne
    private Song song;

    @ManyToOne
    private Album album;
}
