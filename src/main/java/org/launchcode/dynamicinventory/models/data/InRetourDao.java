package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.InRetour;
import org.launchcode.dynamicinventory.models.InRetour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface InRetourDao extends CrudRepository<InRetour,Integer> {
    InRetour findById(int Id);
    List<InRetour> findByOrigineAndDate(String origine, Date date);
    List<InRetour> findByOrigine(String origine);
    List<InRetour> findByDate(Date date);
}
