package BaekGwa.IndexTest.service;

import BaekGwa.IndexTest.entity.Indexing;
import BaekGwa.IndexTest.entity.IndexingDto;
import BaekGwa.IndexTest.entity.NoIndexing;
import BaekGwa.IndexTest.repository.IndexingJpaRepository;
import BaekGwa.IndexTest.repository.NoIndexingJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final IndexingJpaRepository indexingJpaRepository;
    private final NoIndexingJpaRepository noIndexingJpaRepository;

    public void searchByName(String name){
        int numIterations = 10; // 실행 횟수

        long totalDurationIndexed = 0;
        long totalDurationNonIndexed = 0;

        for (int i = 0; i < numIterations; i++) {
            long startIndexed = System.nanoTime();
            List<Indexing> byName = indexingJpaRepository.findByName(name);
            long endIndexed = System.nanoTime();
            totalDurationIndexed += (endIndexed - startIndexed);

            long startNonIndexed = System.nanoTime();
            List<NoIndexing> byName1 = noIndexingJpaRepository.findByName(name);
            long endNonIndexed = System.nanoTime();
            totalDurationNonIndexed += (endNonIndexed - startNonIndexed);
        }

        long averageDurationIndexed = totalDurationIndexed / numIterations;
        long averageDurationNonIndexed = totalDurationNonIndexed / numIterations;

        System.out.println("인덱싱 테이블 평균 처리시간: " + averageDurationIndexed + " ns");
        System.out.println("Full Scan 방식 평균 처리 시간: " + averageDurationNonIndexed + " ns");
    }

    public void searchTeamIdAndBackNumber(Long teamId, Long backNumber){
        int numIterations = 10; // 실행 횟수
        long totalDurationIndexed = 0;
        long totalDurationNonIndexed = 0;

        for (int i = 0; i < numIterations; i++) {
            long startIndexed = System.nanoTime();
//            Indexing byTeamIdAndBackNumber1 = indexingJpaRepository.findByTeamIdAndBackNumber(teamId, backNumber);
            Indexing byBackNumberAndTeamId = indexingJpaRepository.findByBackNumberAndTeamId(backNumber, teamId);
            long endIndexed = System.nanoTime();
            totalDurationIndexed += (endIndexed - startIndexed);

            long startNonIndexed = System.nanoTime();
//            NoIndexing byTeamIdAndBackNumber = noIndexingJpaRepository.findByTeamIdAndBackNumber(teamId, backNumber);
            NoIndexing byBackNumberAndTeamId1 = noIndexingJpaRepository.findByBackNumberAndTeamId(backNumber, teamId);
            long endNonIndexed = System.nanoTime();
            totalDurationNonIndexed += (endNonIndexed - startNonIndexed);
        }

        long averageDurationIndexed = totalDurationIndexed / numIterations;
        long averageDurationNonIndexed = totalDurationNonIndexed / numIterations;

        System.out.println("인덱싱 테이블 평균 처리시간: " + averageDurationIndexed + " ns");
        System.out.println("Full Scan 방식 평균 처리 시간: " + averageDurationNonIndexed + " ns");

    }

    public void coveringIndexTest(Long teamId, Long backNumber){
        int numIterations = 10; // 실행 횟수
        long totalDurationIndexed = 0;
        long totalDurationNonIndexed = 0;

        for (int i = 0; i < numIterations; i++) {
            long startIndexed = System.nanoTime();
            Indexing byBackNumberAndTeamId = indexingJpaRepository.findByBackNumberAndTeamId(backNumber, teamId);
            long endIndexed = System.nanoTime();
            totalDurationIndexed += (endIndexed - startIndexed);

            long startNonIndexed = System.nanoTime();
            IndexingDto teamIdAndBackNumberByTeamIdAndBackNumber = indexingJpaRepository.findTeamIdAndBackNumberByTeamIdAndBackNumber(backNumber, teamId);
            long endNonIndexed = System.nanoTime();
            totalDurationNonIndexed += (endNonIndexed - startNonIndexed);
        }

        long averageDurationIndexed = totalDurationIndexed / numIterations;
        long averageDurationNonIndexed = totalDurationNonIndexed / numIterations;

        System.out.println("인덱싱 테이블 평균 처리시간: " + averageDurationIndexed + " ns");
        System.out.println("Covering 인덱스 방식 평균 처리 시간: " + averageDurationNonIndexed + " ns");

    }
}
