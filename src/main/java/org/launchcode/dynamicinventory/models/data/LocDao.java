package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LocDao extends CrudRepository<Location,Integer> {
    List<Location>findByName(String name);
    //List<Location>findByNameAndByLocationStock(String name, int locationStock);
    List<Location>findByLocationId(int locationId);
    List<Location>findByLocationStock(int locationId);
}
