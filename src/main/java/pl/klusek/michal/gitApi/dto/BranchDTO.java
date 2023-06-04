package pl.klusek.michal.gitApi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BranchDTO {
    private String name;
    private CommitDTO commit;
}
