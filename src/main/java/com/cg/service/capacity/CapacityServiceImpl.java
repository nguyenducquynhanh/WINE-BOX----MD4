package com.cg.service.capacity;

import com.cg.model.Capacity;
import com.cg.repository.CapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CapacityServiceImpl implements ICapacityService{

    @Autowired
    CapacityRepository capacityRepository;
    @Override
    public Iterable<Capacity> findAll() {
        return capacityRepository.findAll();
    }

    @Override
    public Capacity getById(Long id) {
        return null;
    }

    @Override
    public Optional<Capacity> findById(Long id) {
        return capacityRepository.findById(id);
    }

    @Override
    public Capacity save(Capacity capacity) {
        return capacityRepository.save(capacity);
    }

    @Override
    public void remove(Long id) {
        capacityRepository.deleteById(id);
    }
}
