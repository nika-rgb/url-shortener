package com.portfolio.url_shortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.url_shortener.short_url.api.v1.ShortURLRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ShortURLTests {
    public static final String GENERATE_SHORT_URL_PATH = "/api/v1/url-shortener/generate";
    private static final String ORIGINAL_URL = "https://surplus2g.ci/parameters";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("#{new java.net.URL('${url-shortener.base-url}')}")
    private URL urlShortenerBaseUrl;
    @Value("${url-shortener.generated-url-length}")
    private Integer generatedUrlPathLength;

    @Test
    void generateURLShouldReturnOKResponse() throws Exception {

        ShortURLRequest request = new ShortURLRequest(ORIGINAL_URL);

        mockMvc.perform(
                        post(GENERATE_SHORT_URL_PATH)
                                .content(objectMapper.writeValueAsBytes(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void generateURLShouldReturnNonNullableURL() throws Exception {
        ShortURLRequest request = new ShortURLRequest(ORIGINAL_URL);

        ShortURLResponse response = sendGenerateShortUrlRequest(request);

        assertNotNull(response);
        assertNotNull(response.shortUrl());
    }

    @Test
    void generatedURLShouldBeValid() throws Exception {
        ShortURLRequest request = new ShortURLRequest(ORIGINAL_URL);

        ShortURLResponse response = sendGenerateShortUrlRequest(request);

        URL urlObject = new URL(response.shortUrl());

        assertEquals(urlShortenerBaseUrl.getHost(), urlObject.getHost());
        assertEquals(urlShortenerBaseUrl.getProtocol(), urlObject.getProtocol());
        assertTrue(Objects.nonNull(urlObject.getPath()) && !urlObject.getPath().isEmpty());
    }


    @Test
    void generatedUrlPathShouldHaveCorrectLength() throws Exception {
        ShortURLRequest request = new ShortURLRequest(ORIGINAL_URL);

        ShortURLResponse response = sendGenerateShortUrlRequest(request);

        URL urlObject = new URL(response.shortUrl());

        assertEquals(generatedUrlPathLength + 1, urlObject.getPath().length());
    }

    @Test
    void generatedUrlPathShouldBeUniqueForTwoDifferentUrls() throws Exception {
        ShortURLRequest firstRequest = new ShortURLRequest(ORIGINAL_URL);
        ShortURLRequest secondRequest = new ShortURLRequest(ORIGINAL_URL.concat("/").concat("another"));

        ShortURLResponse firstResponse = sendGenerateShortUrlRequest(firstRequest);
        ShortURLResponse secondResponse = sendGenerateShortUrlRequest(secondRequest);

        URL firstUrlObject = new URL(firstResponse.shortUrl());
        URL secondUrlObject = new URL(secondResponse.shortUrl());

        assertTrue(Objects.nonNull(firstUrlObject.getPath()) && !firstUrlObject.getPath().isEmpty());
        assertTrue(Objects.nonNull(secondUrlObject.getPath()) && !secondUrlObject.getPath().isEmpty());
        assertNotEquals(firstUrlObject.getPath(), secondUrlObject.getPath());
    }


    private ShortURLResponse sendGenerateShortUrlRequest(ShortURLRequest request) throws Exception {
        MvcResult result = mockMvc.perform(
                        post(GENERATE_SHORT_URL_PATH)
                                .content(objectMapper.writeValueAsBytes(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        ShortURLResponse response = JsonHelper.getResponse(result.getResponse().getContentAsString(), objectMapper, ShortURLResponse.class);
        return response;
    }

}
