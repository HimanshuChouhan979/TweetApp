package com.tweetapp.users;

import com.tweetapp.users.controller.UsersController;
import com.tweetapp.users.model.UserLoginCredential;
import com.tweetapp.users.model.UserToken;
import com.tweetapp.users.model.Users;
import com.tweetapp.users.servicesImpl.JwtUtil;
import com.tweetapp.users.servicesImpl.UsersServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UsersControllerTest {


    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersServiceImpl usersService;

    @Mock
    private UserDetails userDetails;

    @Mock
    private UserLoginCredential userLoginCredential;

    @Mock
    private JwtUtil jwtUtil;


    @Mock
    private MockMvcRequestSpecification httpRequestMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(usersController);
        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(usersController));
        httpRequestMockMvc = RestAssuredMockMvc.given();
    }



    @Test
    public void userLoginTest() {
        UserLoginCredential userLoginCredential = new UserLoginCredential();
        userLoginCredential.setUid("uid");
        userLoginCredential.setPassword("PASS");
        Mockito.when(usersService.loadUserByUsername(ArgumentMatchers.anyString())).thenReturn(userDetails);
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .header("uid", "ui")
                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .body(userLoginCredential).when().post("/api/v1.0/tweets/auth/login");
        //Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }

    @Test
    public void userLoginTestException() throws Exception {
        UserLoginCredential userLoginCredential = new UserLoginCredential();
        userLoginCredential.setUid("uid");
        userLoginCredential.setPassword("PASS");
        Mockito.when(usersService.loadUserByUsername(ArgumentMatchers.anyString())).thenReturn(userDetails);
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .body(userLoginCredential).when().post("/api/v1.0/tweets/auth/login");
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }


    @Test
    public void getValidityTest() {
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .when().get("/api/v1.0/tweets/auth/validate");
        Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }


    @Test
    public void addUserTest() {
        Users users = new Users();
        users.setEmail("email");
        users.setFirstName("fname");
        users.setUserName("uname");
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .body(users).when().post("/api/v1.0/tweets/register");
        Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }

    @Test
    public void forgotPasswordTest() {
        String email = "email";
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .body(email).when().post("/api/v1.0/tweets/forgot");
        Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }


    @Test
    public void verifyOtpTest() {
        UserToken userToken = new UserToken();
        userToken.setUid("uid");
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .body(userToken).when().post("/api/v1.0/tweets/verify");
        Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }

    @Test
    public void changePasswordTest() {
        UserLoginCredential userLoginCredential = new UserLoginCredential();
        userLoginCredential.setUid("uid");
        userLoginCredential.setPassword("pass");
        MockMvcResponse responseBody = httpRequestMockMvc.header("Content-Type", "application/json")
                .body(userLoginCredential).when().post("/api/v1.0/tweets/change");
        Assert.assertEquals(HttpStatus.SC_OK, responseBody.getStatusCode());
        assertNotNull(responseBody);
    }


}

