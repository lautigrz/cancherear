package com.tallerwebi.repository;

import com.tallerwebi.dominio.Cancha;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RepositorioCanchaImp implements RepositorioCancha {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCanchaImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    @Override
    public void guardarCancha(Cancha cancha) {
        this.sessionFactory.getCurrentSession().save(cancha);
    }

    @Transactional
    @Override
    public List<Cancha> listarCanchasDePublicador(Long idPublicador) {
        String hql = "FROM Cancha c WHERE c.usuario.id = :idPublicador";
        TypedQuery<Cancha> query = this.sessionFactory.getCurrentSession().createQuery(hql, Cancha.class);
        query.setParameter("idPublicador", idPublicador);
        return query.getResultList();

    }


}
