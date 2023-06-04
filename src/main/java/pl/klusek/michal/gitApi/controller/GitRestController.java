package pl.klusek.michal.gitApi.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klusek.michal.gitApi.dto.ResponseDTO;
import pl.klusek.michal.gitApi.service.RepositoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GitRestController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<ResponseDTO>> getRepositoryForUser(@PathVariable("username") String username) {
        List<ResponseDTO> responseDTO = repositoryService.getRepositorysForUsers(username);

        return ResponseEntity.ok().body(responseDTO);
    }
}
