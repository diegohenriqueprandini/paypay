package com.paypay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.paypay.application.EventService;
import com.paypay.domain.entity.Event;
import com.paypay.infra.repository.memory.EventMemoryRepository;
import com.paypay.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class EventApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventMemoryRepository eventMemoryRepository;

    @Test
    void deveRetornarUmaListaVazia() throws Exception {
        String json = mockMvc.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        assertThat(json).isEqualTo("[]");
        List<EventService.EventOutput> output = JsonUtils.fromJson(json, new TypeReference<>() {
        });
        assertThat(output).isEmpty();
    }

    @Test
    void deveRetornarUmaListaComUmEvento() throws Exception {
        UUID id = UUID.randomUUID();
        Event event = new Event(id, "A");
        eventMemoryRepository.save(event);

        String json = mockMvc.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        List<EventService.EventOutput> output = JsonUtils.fromJson(json, new TypeReference<>() {
        });
        assertThat(output).hasSize(1);
        assertThat(output.get(0).id()).isEqualTo(id);
        assertThat(output.get(0).name()).isEqualTo("A");
    }
}
