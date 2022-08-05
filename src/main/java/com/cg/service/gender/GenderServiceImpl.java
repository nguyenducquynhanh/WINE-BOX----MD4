package com.cg.service.gender;

import com.cg.model.Category;
import com.cg.model.Gender;
import com.cg.repository.CategoryRepository;
import com.cg.repository.GenderRepository;
import com.cg.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GenderServiceImpl implements IGenderService {

    @Autowired
    private GenderRepository genderRepository;


    @Override
    public Iterable<Gender> findAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getById(Long id) {
        return null;
    }

    @Override
    public Optional<Gender> findById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    public Gender save(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public void remove(Long id) {
        genderRepository.deleteById(id);
    }
}
