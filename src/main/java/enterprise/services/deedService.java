
package enterprise.services;

import enterprise.models.Deed;
import enterprise.repository.DeedRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class deedService {

    private DeedRepository deedRepository;

    public deedService(DeedRepository deedRepository) {
        this.deedRepository = deedRepository;
    }

    public List<Deed> showTodolist() {
        return (List<Deed>) deedRepository.findAll();
    }

    public Deed showDeed(int id) {

        Deed deed = new Deed();

        Optional<Deed> element = deedRepository.findById(id);

        if (element.isPresent()) {
            deed = element.get();
        }

        return deed;
    }

    public boolean existingDeed(int id) {

        boolean result;

        result = showTodolist().contains(showDeed(id));

        return result;
    }

    public void saveDeed(Deed deed) {
        deedRepository.save(deed);
    }

    public void editDeed(int id, Deed deed) {

        Deed updatedDeed = deedRepository.findById(id).orElseThrow();

        updatedDeed.setName(deed.getName());

        deedRepository.save(updatedDeed);
    }

    public void deleteDeed(int id) {

        Deed deed = deedRepository.findById(id).orElseThrow();

        deedRepository.delete(deed);
    }

    public void deleteAllDeeds() {
        deedRepository.deleteAll();
    }
}
