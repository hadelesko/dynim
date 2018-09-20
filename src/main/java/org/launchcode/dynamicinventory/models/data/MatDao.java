package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.MMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MatDao extends CrudRepository<MMaterial,Integer> {


    MMaterial removeByMatName(String matName);
    MMaterial findByMatId(int matId);
    MMaterial removeByMatNameAndStockLessThanEqual(String matName, double stock);
/*    Iterable<MMaterial> getAllMaterials();
    Iterable<MMaterial> getbysupplier(String suppliername);
    MMaterial getById(int id);
    MMaterial getByname(String name);*/

}
