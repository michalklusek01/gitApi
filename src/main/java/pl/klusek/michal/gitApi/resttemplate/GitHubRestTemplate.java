package pl.klusek.michal.gitApi.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.klusek.michal.gitApi.dto.BranchDTO;
import pl.klusek.michal.gitApi.dto.GitHubRepositoryDTO;


import java.util.Arrays;
import java.util.List;

@Component
public class GitHubRestTemplate {
    private final String API_URL = "https://api.github.com/";
    private final String ACCEPT_HEADER = "Accept";
    RestTemplate restTemplate;
    HttpHeaders headers;

    @Autowired
    public GitHubRestTemplate() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
    }

    public List<GitHubRepositoryDTO> getRepositoriesForUser(String username) {
        String URL = API_URL + "users/" + username + "/repos?fork=false";
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Accept", ACCEPT_HEADER);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GitHubRepositoryDTO[]> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, GitHubRepositoryDTO[].class);
        GitHubRepositoryDTO[] repositories = responseEntity.getBody();
        return Arrays.asList(repositories);
    }

    public List<BranchDTO> getBranches(GitHubRepositoryDTO repository) {
        String URL = API_URL + "repos/" + repository.getOwner().getLogin() + "/" + repository.getName() + "/branches";
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Accept", ACCEPT_HEADER);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<BranchDTO[]> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, BranchDTO[].class);
        BranchDTO[] branches = responseEntity.getBody();
        return Arrays.asList(branches);
    }

    public boolean checkIfGitUserExist(String username) {
        String url = API_URL + "users/" + username;

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return true;
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        }
    }
}