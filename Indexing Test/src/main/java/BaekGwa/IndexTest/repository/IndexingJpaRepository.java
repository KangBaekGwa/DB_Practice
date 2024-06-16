package BaekGwa.IndexTest.repository;

import BaekGwa.IndexTest.entity.Indexing;
import BaekGwa.IndexTest.entity.IndexingDto;
import BaekGwa.IndexTest.entity.NoIndexing;
import jakarta.persistence.Index;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexingJpaRepository extends JpaRepository<Indexing, Long> {

    List<Indexing> findByName(String name);
    List<Indexing> findByTeamIdGreaterThanAndBackNumberGreaterThan(Long teamId, Long backNumber);

    Indexing findByTeamIdAndBackNumber(Long teamId, Long backNumber);

    //반대로 검색해버리기
    Indexing findByBackNumberAndTeamId(Long backNumber, Long teamId);

    @Query("SELECT new BaekGwa.IndexTest.entity.IndexingDto(i.teamId, i.backNumber) " +
            "FROM Indexing i WHERE i.teamId = :teamId AND i.backNumber = :backNumber")
    IndexingDto findTeamIdAndBackNumberByTeamIdAndBackNumber(
            @Param("teamId") Long teamId,
            @Param("backNumber") Long backNumber
    );

}
