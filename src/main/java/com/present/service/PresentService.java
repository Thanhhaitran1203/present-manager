package com.present.service;

import com.present.model.Present;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PresentService implements IPresentService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Present> findAll() {
        String queryStr = "select p from Present as p";
        TypedQuery<Present> query = entityManager.createQuery(queryStr,Present.class);
        return query.getResultList();
    }
    @Override
    public void save(Present present) {
        Transaction transaction = null;
        Present origin;
        if (present.getId() == 0){
            origin = new Present();
        }else {
            origin = findById(present.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(present.getCode());
            origin.setName(present.getName());
            origin.setPrice(present.getPrice());
            origin.setShip(present.getShip());
            origin.setImg(present.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        }catch (Exception e){
         e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public Present findById(int id) {
        String queryStr = "SELECT p FROM Present AS p WHERE p.id = :id";
        TypedQuery<Present> query = entityManager.createQuery(queryStr, Present.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Present present = findById(id);
        if (present != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(present);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
