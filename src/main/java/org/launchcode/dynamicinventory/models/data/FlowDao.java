package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.Flow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FlowDao extends CrudRepository<Flow,Integer> {

    void  removeByName(String name);

    Flow findByFlowId(int flowId);

    Flow findByName(String name);
}