package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> findDistilleryByParticularRegion(String region) {
        List<Distillery> result = null;
        Session session = entityManager.unwrap(Session.class);
        Criteria cr = session.createCriteria(Distillery.class);
        cr.add(Restrictions.eq("region", region));
        result = cr.list();
        return result;
    }

    @Transactional
    public List<Distillery> findDistilleriesThatHaveOldWhiskies(int age) {
        List<Distillery> result = null;
        Session session = entityManager.unwrap(Session.class);
        try{
            Criteria cr = session.createCriteria(Distillery.class);
            cr.createAlias("whiskies", "whisky");
            cr.add(Restrictions.eq("whisky.age", age));
            result = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }
}
