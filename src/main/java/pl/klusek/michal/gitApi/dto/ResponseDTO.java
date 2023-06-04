package pl.klusek.michal.gitApi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDTO> branches;

    public ResponseDTO(String repositoryName, String ownerLogin, List<BranchDTO> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }
}
