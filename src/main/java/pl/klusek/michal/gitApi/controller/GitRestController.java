package pl.klusek.michal.gitApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.klusek.michal.gitApi.dto.ErrorResponseDTO;
import pl.klusek.michal.gitApi.dto.ResponseDTO;
import pl.klusek.michal.gitApi.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GitRestController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<?>> getRepositoryForUser(@PathVariable("username") String username,
                                                        @RequestHeader("Accept") String acceptHeader) {
        List<ErrorResponseDTO> errorList = new ArrayList<>();

        if ("application/xml".equals(acceptHeader)) {
            errorList.add(new ErrorResponseDTO(406, "Unsupported Media Type"));
            return ResponseEntity.status(406).header("Conent-Type", "application/xml").body(errorList);
        }
        if (!repositoryService.checkIfGitUserExist(username)) {
            errorList.add(new ErrorResponseDTO(404, "User not found: " + username));
            return ResponseEntity.status(404).body(errorList);
        }

        List<ResponseDTO> responseDTO = repositoryService.getRepositorysForUsers(username);
        return ResponseEntity.ok().body(responseDTO);
    }
}
