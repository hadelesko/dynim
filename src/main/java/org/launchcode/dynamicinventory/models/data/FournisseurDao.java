package org.launchcode.dynamicinventory.models.data;


import org.launchcode.dynamicinventory.models.Fournisseur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FournisseurDao extends CrudRepository<Fournisseur, Integer> {
    //List<Fournisseur> findAll();
    Fournisseur removeByName(String name);

    Fournisseur findByFournisseurId(int fournisseurId);

    Fournisseur findByName(String name);

    Fournisseur findByEmail(String email);
}
