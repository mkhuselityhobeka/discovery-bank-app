package com.discovery.repository;

import com.discovery.model.Atm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtmRepository extends CrudRepository<Atm,Integer> {
                List<Atm>findByAtmIdAndAtmAllocationId(Integer atmId, Integer atmAllocationId);
}
