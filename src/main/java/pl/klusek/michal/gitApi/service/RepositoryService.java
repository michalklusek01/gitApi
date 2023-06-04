package pl.klusek.michal.gitApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.gitApi.dto.BranchDTO;
import pl.klusek.michal.gitApi.dto.GitHubRepositoryDTO;
import pl.klusek.michal.gitApi.dto.ResponseDTO;
import pl.klusek.michal.gitApi.resttemplate.GitHubRestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryService {

    @Autowired
    private GitHubRestTemplate gitHubRestTemplate;

    public List<ResponseDTO> getRepositorysForUsers(String username) {
        List<GitHubRepositoryDTO> gitHubRepositoryDTO = gitHubRestTemplate.getRepositoriesForUser(username);
        List<ResponseDTO> responseDTOList = new ArrayList<>();

        for (GitHubRepositoryDTO repository : gitHubRepositoryDTO) {
            List<BranchDTO> branchesFromRepository = getBranchesFromRepository(repository);
            ResponseDTO responseDTO = new ResponseDTO(
                    repository.getName(),
                    repository.getOwner().getLogin(),
                    branchesFromRepository);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<BranchDTO> getBranchesFromRepository(GitHubRepositoryDTO repository) {
        return gitHubRestTemplate.getBranches(repository);
    }
}

