package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LocDao extends CrudRepository<Location,Integer> {
    Location findByName(String name);
    //List<Location>findByNameAndByLocationStock(String name, int locationStock);
    Location findByLocationId(int locationId);
    List<Location>findByLocationStock(double locationStock);
}
