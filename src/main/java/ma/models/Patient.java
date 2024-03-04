package ma.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "patients")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
    @OneToMany(mappedBy = "patient", fetch = LAZY)
    private Collection<RendezVous> rendezVous;

}
