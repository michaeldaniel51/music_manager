package danny.musicmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danny.musicmanager.enums.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    private MusicGenre genre;

    @Column(name = "DATE_RELEASED", updatable = false)
    private LocalDateTime dateReleased = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "song",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reaction> reaction;

    @OneToMany(mappedBy = "song",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comment;
}
