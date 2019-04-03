package test.task.rusoft.autoserv.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientsControllerTest {

  private static final String CLIENT_NAME = "Winston";
  private static final String CAR_MODEL = "Toyota Chaser";

  private static final String POST_REQ_CONTENT = "{\n"
      + "  \"clientName\": \""+CLIENT_NAME+"\",\n"
      + "  \"clientBirthYear\": \"1984\",\n"
      + "  \"carModel\": \""+CAR_MODEL+"\"\n"
      + "}";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void addClient() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .post("/clients")
        .content(POST_REQ_CONTENT)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void removeClient() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/clients")
        .param("clientName", CLIENT_NAME)
        .param("carModel", CAR_MODEL)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }


}
