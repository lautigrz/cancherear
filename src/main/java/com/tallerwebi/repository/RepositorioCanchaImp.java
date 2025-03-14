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

    @Transactional
    @Override
    public Boolean cambiarEstadoDeCancha(Long idCancha) {
        String hql = "UPDATE Cancha c SET c.estado = CASE WHEN c.estado = true THEN false ELSE true END WHERE c.id = :idCancha";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idCancha", idCancha);
        query.executeUpdate();

        return obtenerEstadoCancha(idCancha);
    }

    @Transactional
    public Boolean obtenerEstadoCancha(Long idCancha) {
        String hql = " SELECT c.estado FROM Cancha c WHERE c.id = :idCancha";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idCancha", idCancha);
        return (Boolean) query.getSingleResult();
    }

}
