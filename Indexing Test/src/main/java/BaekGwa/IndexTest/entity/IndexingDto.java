package BaekGwa.IndexTest.entity;

import lombok.Data;

@Data
public class IndexingDto {
    public Long teamId;
    public Long backNumber;

    public IndexingDto(Long teamId, Long backNumber) {
        this.teamId = teamId;
        this.backNumber = backNumber;
    }
}
