package com.hybernatejpa.dao;

import com.hybernatejpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//Specialized Annotation for DAOs
//Spring provide the @Repository annotation (This is sub-annotation of @Component)
@Repository
public class StudentDAOImpl implements StudentDAO{

    //Define Field for EntityManager
    private EntityManager entityManager;

    //Inject EntityManager Using Constructor Injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional //Transactional is from spring framework <============> Automagically begin and end a transactional for your JPA Code.
    public void save(Student student) {
        //Save the Java object
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id){
        return entityManager.find(Student.class ,4);
    }

    @Override
    public List<Student> findAll() {
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName desc ",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData",Student.class);

        //Set Query Parameter
        theQuery.setParameter("theData",lastName);

        return theQuery.getResultList();


    }

    @Override
    @Transactional
    public void update(Student student) {
        //update the student
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve the student
        Student student = entityManager.find(Student.class,id);

        //delete the student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAllStudent() {
        return entityManager.createQuery("DELETE  FROM Student").executeUpdate();
    }
}
