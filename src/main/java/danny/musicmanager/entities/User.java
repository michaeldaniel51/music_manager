package danny.musicmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import danny.musicmanager.enums.Gender;
import danny.musicmanager.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERNAME",unique = true)
    @NotNull(message = "username is required ")
    private String username;

    @Column(name = "PASSWORD")
    @NotNull(message = "password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email(message = "Malformed email")
    @NotNull(message = "email is required")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATE_OF_BIRTH")
    @NotNull(message = "please enter your date of birth")
    private LocalDate dateOfBirth;

    @Column(name = "STATE_OF_ORIGIN")
    @NotNull(message = "please enter your state of origin")
    private String stateOfOrigin;

    @Column(name = "LGA_OF_RESIDENCE")
    @NotNull(message = "please enter your local government of residence")
    private String lgaOfResidence;

    @Column(name = "OCCUPATION")
    @NotNull(message = "please state your occupation")
    private String occupation;

    @Column(name = "MARITAL_STATUS")
    @NotNull(message = "Enter marital status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "PHONE_NUMBER")
    @NotNull(message = "Enter a valid phone number")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Song> song = new ArrayList<>();

    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Reaction> reaction;

    public void setPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @JsonIgnore
    @Transient
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
