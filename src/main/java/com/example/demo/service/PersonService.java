package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: SBService
 * @Package com.demo.service
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/28 16:49
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * @param id
     * @return
     */
    public Person findone(Integer id){
        return personRepository.findOne(id);
    }
}
