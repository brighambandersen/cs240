package services;

import daos.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import results.PersonIdResult;
import results.PersonIdResult;
import results.RegisterResult;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PersonIdServiceTest {
    private PersonIdService personIdService;
    private String authToken;
    private String personId;

    @BeforeEach
    void setUp() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();

        RegisterRequest registerRequest = new RegisterRequest("brighamband", "password",
                "brighamband@gmail.com", "Brigham", "Andersen", "m");
        RegisterService registerService = new RegisterService();
        RegisterResult registerResult = registerService.register(registerRequest);
        authToken = registerResult.getAuthToken();
        personId = registerResult.getPersonId();

        // FIXME - Have this call the load service to generate dummy person data

        personIdService = new PersonIdService();
    }

    @Test
    void testRunPersonIdPass() throws DataAccessException {
        Path GOOD_URL_PATH = Path.of("/person/" + personId);
        PersonIdResult personIdResult = personIdService.runPersonId(authToken, GOOD_URL_PATH);

        assertTrue(personIdResult.isSuccess());
        assertNull(personIdResult.getMessage());
        assertEquals(personId, personIdResult.getPersonId());
    }

    /**
     * Makes request with invalid auth token
     */
    @Test
    void testRunPersonIdFail() throws DataAccessException {
        String BAD_AUTH_TOKEN = "badauthtoken";
        String RANDOM_PERSON_ID = "randompersonid";
        Path BAD_URL_PATH = Path.of("/person/" + RANDOM_PERSON_ID);

        PersonIdResult personIdResult = personIdService.runPersonId(BAD_AUTH_TOKEN, BAD_URL_PATH);

        assertFalse(personIdResult.isSuccess());
        assertEquals("Error: Invalid auth token", personIdResult.getMessage());
    }
}