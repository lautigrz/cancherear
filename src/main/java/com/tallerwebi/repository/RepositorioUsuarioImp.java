package com.tallerwebi.repository;

import com.tallerwebi.dominio.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioImp implements RepositorioUsuario {

  private SessionFactory sessionFactory;

  @Autowired
  public RepositorioUsuarioImp(SessionFactory sessionFactory){
      this.sessionFactory = sessionFactory;
  }

    @Override
    public void guardarUsuario(Usuario usuario) {
        this.sessionFactory.getCurrentSession().save(usuario);
    }
}
