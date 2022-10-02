package task.methoda.methoda_task.beans;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int id;
    @Column(unique = true, length = 40, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(length = 40, nullable = false)
    private StatusLabel label;
}
