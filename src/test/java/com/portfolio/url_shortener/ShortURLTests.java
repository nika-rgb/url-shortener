package com.portfolio.url_shortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.url_shortener.short_url.api.v1.ShortURLRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ShortURLTests {
    private static final String ORIGINAL_URL = "https://surplus2g.ci/parameters";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void generateURLShouldReturnOKResponse() throws Exception {

        ShortURLRequest request = buildRequest(ORIGINAL_URL);

        mockMvc.perform(
                post("/api/v1/url-shortener/generate")
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    private ShortURLRequest buildRequest(String url) throws MalformedURLException {
        return new ShortURLRequest(url);
    }

}
