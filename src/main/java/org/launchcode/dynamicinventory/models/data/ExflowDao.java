package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Eflow;
import org.launchcode.dynamicinventory.models.Fournisseur;
import org.launchcode.dynamicinventory.models.MMaterial;
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
    List<Eflow> findByMaterial(MMaterial material);
    List<Eflow> findByDescription(String description);
    List<Eflow> findByFournisseur(Fournisseur fournisseur);
}