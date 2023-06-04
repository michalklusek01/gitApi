package pl.klusek.michal.gitApi.resttemplate;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.klusek.michal.gitApi.dto.BranchDTO;
import pl.klusek.michal.gitApi.dto.GitHubRepositoryDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class GitHubRestTemplate {
    private final String API_URL = "https://api.github.com/";
    RestTemplate restTemplate;

    //TODO
    public GitHubRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    public List<GitHubRepositoryDTO> getRepositoriesForUser(String username) {
        String URL = API_URL + "users/" + username + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GitHubRepositoryDTO[]> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, GitHubRepositoryDTO[].class);
        GitHubRepositoryDTO[] repositories = responseEntity.getBody();
        return Arrays.asList(repositories);
    }

    public List<BranchDTO> getBranches(GitHubRepositoryDTO repository) {
        String URL = API_URL + "repos/" + repository.getOwner().getLogin() + "/" + repository.getName() + "/branches";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<BranchDTO[]> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, BranchDTO[].class);
        BranchDTO[] branches = responseEntity.getBody();
        return Arrays.asList(branches);
    }
}