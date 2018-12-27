package org.launchcode.dynamicinventory.models.data;

import org.launchcode.dynamicinventory.models.MaterialOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface IntorderDao extends CrudRepository<MaterialOrder,Integer> {
    MaterialOrder findByOrderId(int orderId);
    List<MaterialOrder> findByDestinationAndDate(String destination, Date date);
    List<MaterialOrder> findByDestination(String destination);
    List<MaterialOrder> findByDate(Date date);
}
