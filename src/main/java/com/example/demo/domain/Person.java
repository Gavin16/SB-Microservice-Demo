package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Title: SBService
 * @Package com.demo.domain
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/28 9:16
 */
@Entity
public class Person {

    // 这里hibernate强行要求设置Id作为主键,@Id,@GeneratedValue 均需要加上
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String nation;
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", nation='" + nation + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
