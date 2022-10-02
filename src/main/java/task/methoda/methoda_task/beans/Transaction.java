package task.methoda.methoda_task.beans;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;
    @Column(unique = true, length = 40, nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name = "to_who")
    private Status toWho;
    @OneToOne
    @JoinColumn(name = "from_where")
    private Status fromWhere;
}
