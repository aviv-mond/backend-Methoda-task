package task.methoda.methoda_task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.methoda.methoda_task.beans.Status;
import task.methoda.methoda_task.beans.StatusLabel;
import task.methoda.methoda_task.repositories.StatusRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final StatusRepository statusRepository;


    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }


    public void deleteStatusById(int id) {
        Status status = getById(id);
        statusRepository.deleteById(id);
        Status nextInitStatus = statusRepository.findFirstByIdGreaterThan(0);
        if (status.getLabel() == StatusLabel.INITIAL_STATUS) {
            nextInitStatus.setLabel(StatusLabel.INITIAL_STATUS);
            statusRepository.save(nextInitStatus);
        }
    }

    public void addNewStatus(String statusName) {
        List<Status> allStatuses = statusRepository.findAll();
        if (allStatuses.isEmpty()) {
            saveStatus(Status.builder()
                    .name(statusName).
                    label(StatusLabel.INITIAL_STATUS)
                    .build());
        }
        saveStatus(Status.builder()
                .name(statusName)
                .label(StatusLabel.FINAL)
                .build());
    }

    public void deleteAllStatuses() {
        statusRepository.deleteAll();
    }

    public void saveStatus(Status status) {
        statusRepository.save(status);
    }

    public Status getByName(String name) {
        return statusRepository.getByName(name);
    }

    public Status getById(int id) {
        return statusRepository.getReferenceById(id);
    }
}
