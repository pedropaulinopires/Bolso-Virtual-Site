package com.bolsovirtual.br.repository;

import com.bolsovirtual.br.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("SELECT u FROM Usuario u where u.email = :email")
    Usuario buscarUsuarioPorEmail(@Param("email") String email);
}
