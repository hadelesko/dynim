package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.EFlow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ExflowDao extends CrudRepository<EFlow,Integer> {
    //List<EFlow> findAll();
    //void removeByName(String name);
    EFlow findById(int id);
    List<EFlow> findByDescription(String description);

}