package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.ExRetour;
import org.launchcode.dynamicinventory.models.InRetour;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ExRetourDao extends CrudRepository<ExRetour,Integer> {
    ExRetour findById(int Id);
    List<ExRetour> findByOrigineAndDate(String origine, Date date);
    List<ExRetour> findByOrigine(String origine);
    List<ExRetour> findByDate(Date date);
}
