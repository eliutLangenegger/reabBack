package cl.gendarmeria.reabback.security.repository;

import cl.gendarmeria.reabback.security.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByMail(String email);

    Page<Usuario> findAll(Pageable pageable);

    List<Usuario> findAllByUnidad(String subdep);

    List<Usuario> findAllByUnidadAndCargo(String subdep, String cargo);


}
