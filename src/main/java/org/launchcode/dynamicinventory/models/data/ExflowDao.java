package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Eflow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ExflowDao extends CrudRepository<Eflow,Integer> {
    //List<Eflow> findAll();
    //void removeByName(String name);
    Eflow findById(int id);
    List<Eflow> findByDescription(String description);

}