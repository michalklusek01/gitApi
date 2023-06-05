package pl.klusek.michal.gitApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDTO> branches;
}
