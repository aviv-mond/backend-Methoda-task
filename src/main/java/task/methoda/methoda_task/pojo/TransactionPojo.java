package task.methoda.methoda_task.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TransactionPojo {
    private String transactionName;
    private String fromWhoStatusName;
    private String toWhoStatusName;
}
