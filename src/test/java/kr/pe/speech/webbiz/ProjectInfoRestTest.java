package kr.pe.speech.webbiz;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectInfoRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProjectInfo() throws Exception {
        this.mockMvc.perform(
                        post("/project")
                                .content("{\"projectName\":\"MyProject0\", \"projectType\":\"Other\"}").
                                header(HttpHeaders.CONTENT_TYPE, "application/json")).
                andDo( print()).andExpect( status().isOk() );
    }

    @Test
    public void testSetup() throws Exception {
        this.mockMvc.perform(
                        post("/url")
                                .content("{\"domainurl\":\"speech.pe.kr\", \"sourcelanguage\":\"Korean\", \"urltype\":\"Subdomains\" }")
                                        .header(HttpHeaders.CONTENT_TYPE, "application/json")).
                andDo( print()).andExpect( status().isOk() );
    }



}
