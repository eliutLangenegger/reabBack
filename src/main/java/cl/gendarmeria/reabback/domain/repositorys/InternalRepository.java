package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.daosEntitys.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Repository
public interface InternalRepository extends JpaRepository<Internal, String> {
}
