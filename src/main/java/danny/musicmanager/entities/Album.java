package danny.musicmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ALBUM_NAME")
    private String name;

    @Column(name = "DATE_RELEASED")
    private LocalDate dateReleased;

    @ManyToOne
    private User user;




}
