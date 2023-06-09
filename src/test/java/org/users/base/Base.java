package org.users.base;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.users.constant.Parameters;
import java.util.HashMap;
import java.util.Map;

public class Base {
    protected Map<String, String> headers = new HashMap<>();
    protected Playwright playwright;
    protected APIRequestContext apiRequestContext;
    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL(Parameters.USERS));
        headers.put("Accept", "application/json");
        System.out.println("---------------------------------------------------------");
    }

    @AfterTest
    public void tearDown() {
        apiRequestContext.dispose();
        playwright.close();
        System.out.println("---------------------------------------------------------");
    }
}
