package pl.klusek.michal.gitApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.klusek.michal.gitApi.controller.GitRestController;
import pl.klusek.michal.gitApi.dto.ErrorResponseDTO;
import pl.klusek.michal.gitApi.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class RepositoryServiceTest {
    @Mock
    RepositoryService repositoryService;

    @InjectMocks
    GitRestController gitRestController;

    @Test
    public void invalidUsernameTest() {
        String username = "noexistuser";
        String acceptHeader = "application/json";

        List<ErrorResponseDTO> list = new ArrayList<>();
        list.add(new ErrorResponseDTO(404, "User not found: " + username));

        Mockito.when(this.repositoryService.checkIfGitUserExist(username)).thenReturn(false);
        ResponseEntity<List<?>> response = gitRestController.getRepositoryForUser(username, acceptHeader);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals(list, response.getBody());

        Mockito.verify(repositoryService, Mockito.times(1)).checkIfGitUserExist(username);
        Mockito.verifyNoMoreInteractions(repositoryService);
    }
}
