package com.tallerwebi.repository;

import com.tallerwebi.dominio.Deportivo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioDeportivoImp implements RepositorioDeportivo {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDeportivoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void guardar(Deportivo deportivo) {
        this.sessionFactory.getCurrentSession().save(deportivo);
    }
}
