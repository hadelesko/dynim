package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocDao extends CrudRepository<Location,Integer> {
}
