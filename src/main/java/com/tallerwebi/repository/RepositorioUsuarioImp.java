package com.tallerwebi.repository;

import com.tallerwebi.dominio.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioUsuarioImp implements RepositorioUsuario {

  private SessionFactory sessionFactory;

  @Autowired
  public RepositorioUsuarioImp(SessionFactory sessionFactory){
      this.sessionFactory = sessionFactory;
  }

    @Transactional
    @Override
    public void guardarUsuario(Usuario usuario)
    {
        this.sessionFactory.getCurrentSession().save(usuario);
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioPorCredenciales(String email, String password) {
        Usuario usuario = buscarUsuarioPorEmail(email);

        if(usuario != null && usuario.getPassword().equals(password)){
            return usuario;
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean existeUsuario(String email) {
        return buscarUsuarioPorEmail(email) != null;
    }


    @Transactional
    public Usuario buscarUsuarioPorEmail(String email) {
        return (Usuario) this.sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }
}
