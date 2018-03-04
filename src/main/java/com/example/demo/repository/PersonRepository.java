package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Title: SBService
 * @Package com.demo.repository
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/28 16:54
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {

}
