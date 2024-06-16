package BaekGwa.IndexTest.repository;

import BaekGwa.IndexTest.entity.Indexing;
import BaekGwa.IndexTest.entity.NoIndexing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoIndexingJpaRepository extends JpaRepository<NoIndexing, Long> {

    List<NoIndexing> findByName(String name);

    List<NoIndexing> findByTeamIdGreaterThanAndBackNumberGreaterThan(Long teamId, Long backNumber);

    NoIndexing findByTeamIdAndBackNumber(Long teamId, Long backNumber);

    NoIndexing findByBackNumberAndTeamId(Long backNumber, Long teamId);
}
