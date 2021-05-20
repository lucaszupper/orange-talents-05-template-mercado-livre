package br.com.zup.mercadolivre.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
