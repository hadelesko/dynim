package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SupplierDao extends CrudRepository<Supplier,Integer> {

    List<Supplier> findAll();
    Supplier findByName(String name);
    Supplier findBySupplierId(int supplierId);
    List<Supplier> removeByName(String name);
    //void updateMaterials(List<MMaterial>materials);
    //List<MMaterial> findByMaterialAndSupplierId(MMaterial material, int supplierId);
    //Alternative
    //List<MMaterial>findByMatIdAndSupplierName(int materialId, String supplierName);
    ////List<Supplier> removeByMatNameAndStockLessThanEqual(String matName, double stock);
/*    Iterable<MMaterial> getAllMaterials();
    Iterable<MMaterial> getbysupplier(String suppliername);
    MMaterial getById(int id);
    MMaterial getByname(String name);*/

}
