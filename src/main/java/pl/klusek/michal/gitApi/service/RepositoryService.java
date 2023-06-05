package pl.klusek.michal.gitApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.gitApi.dto.BranchDTO;
import pl.klusek.michal.gitApi.dto.GitHubRepositoryDTO;
import pl.klusek.michal.gitApi.dto.ResponseDTO;
import pl.klusek.michal.gitApi.resttemplate.GitHubRestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    @Autowired
    private GitHubRestTemplate gitHubRestTemplate;

    public List<ResponseDTO> getRepositorysForUsers(String username) {
        List<GitHubRepositoryDTO> gitHubRepositoryDTO = gitHubRestTemplate.getRepositoriesForUser(username);
        List<ResponseDTO> responseDTOList = gitHubRepositoryDTO.stream()
                .map(repository -> {
                    List<BranchDTO> branchesFromRepository = getBranchesFromRepository(repository);
                    return new ResponseDTO(
                            repository.getName(),
                            repository.getOwner().getLogin(),
                            branchesFromRepository
                    );
                })
                .collect(Collectors.toList());

        return responseDTOList;
    }

    public List<BranchDTO> getBranchesFromRepository(GitHubRepositoryDTO repository) {
        return gitHubRestTemplate.getBranches(repository);
    }

    public boolean checkIfGitUserExist(String username){
        return this.gitHubRestTemplate.checkIfGitUserExist(username);
    }
}

