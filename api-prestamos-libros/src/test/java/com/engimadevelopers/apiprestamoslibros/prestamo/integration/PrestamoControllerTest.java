package com.engimadevelopers.apiprestamoslibros.prestamo.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.controller.PrestamoRestController;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearRequest;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearResponse;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.repository.PrestamoRepository;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.service.PrestamoServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PrestamoServiceImpl prestamoServiceImpl;

    @Autowired
    PrestamoRepository prestamoRepository;

    @Transactional
    private void reiniciarBD() {
        prestamoRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
        this.reiniciarBD();
    }

    @Test
    public void obtenerPrestamos() throws Exception {

        PrestamoRestController prestamoRestController = new PrestamoRestController(prestamoServiceImpl);
        PrestamoCrearRequest prestamoCrearRequest = new PrestamoCrearRequest();

        prestamoCrearRequest.setIsbn("X789ASD");
        prestamoCrearRequest.setTipoUsuario(2);
        prestamoCrearRequest.setIdentificacionUsuario("SD79QWW");

        PrestamoCrearResponse prestamoCrearResponse = prestamoRestController.crear(prestamoCrearRequest).getBody();

        mockMvc.perform(get("/prestamo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(prestamoCrearResponse.getId()))
                .andExpect(
                        jsonPath("$[0].fechaMaximaDevolucion").value(prestamoCrearResponse.getFechaMaximaDevolucion()))
                .andExpect(jsonPath("$[0].isbn").exists())
                .andExpect(jsonPath("$[0].identificacionUsuario").exists())
                .andExpect(jsonPath("$[0].tipoUsuario").exists());

    }

    @Test
    public void testObtenerPrestamoPorId() throws Exception {

        PrestamoRestController prestamoRestController = new PrestamoRestController(prestamoServiceImpl);
        PrestamoCrearRequest prestamoCrearRequest = new PrestamoCrearRequest();

        prestamoCrearRequest.setIsbn("D789SA");
        prestamoCrearRequest.setTipoUsuario(1);
        prestamoCrearRequest.setIdentificacionUsuario("23313ASDS");

        PrestamoCrearResponse prestamoCrearResponse = prestamoRestController.crear(prestamoCrearRequest).getBody();

        mockMvc.perform(get("/prestamo/{id}", prestamoCrearResponse.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(prestamoCrearResponse.getId()))
                .andExpect(jsonPath("$.fechaMaximaDevolucion").value(prestamoCrearResponse.getFechaMaximaDevolucion()));
    }

    @Test
    public void testCrearPrestamo() throws Exception {

        String prestamoJson = "{\"isbn\":\"ASD018A\",\"identificacionUsuario\":\"569SD789A\",\"tipoUsuario\":2}";
        mockMvc.perform(post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(prestamoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fechaMaximaDevolucion").exists());

    }

    @Test
    public void testEliminarPrestamo() throws Exception {
        PrestamoRestController prestamoRestController = new PrestamoRestController(prestamoServiceImpl);
        PrestamoCrearRequest prestamoCrearRequest = new PrestamoCrearRequest();

        prestamoCrearRequest.setIsbn("ASXSAD");
        prestamoCrearRequest.setTipoUsuario(1);
        prestamoCrearRequest.setIdentificacionUsuario("789ASD");

        PrestamoCrearResponse prestamoCrearResponse = prestamoRestController.crear(prestamoCrearRequest).getBody();

        mockMvc.perform(delete("/prestamo/{id}", prestamoCrearResponse.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void testLibroYaFuePrestado() throws Exception {

        PrestamoRestController prestamoRestController = new PrestamoRestController(prestamoServiceImpl);
        PrestamoCrearRequest prestamoCrearRequest = new PrestamoCrearRequest();

        prestamoCrearRequest.setIsbn("ASXSAD");
        prestamoCrearRequest.setTipoUsuario(1);
        prestamoCrearRequest.setIdentificacionUsuario("123456789");

        prestamoRestController.crear(prestamoCrearRequest);

        String prestamoJson = "{\"isbn\":\"ASXSAD\",\"identificacionUsuario\":\"9764621\",\"tipoUsuario\":1}";
        MvcResult result = mockMvc.perform(post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(prestamoJson))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        String mensajeEsperado = "El libro con ISBN ASXSAD ya se encuentra prestado, deberas buscar otro libro";
        assertTrue(jsonResponse.has("mensaje"));
        assertEquals(mensajeEsperado, jsonResponse.get("mensaje").asText());

    }

    @Test
    public void testUsuarioInvitado() throws Exception {

        PrestamoRestController prestamoRestController = new PrestamoRestController(prestamoServiceImpl);
        PrestamoCrearRequest prestamoCrearRequest = new PrestamoCrearRequest();

        prestamoCrearRequest.setIsbn("ASXSAD");
        prestamoCrearRequest.setTipoUsuario(3);
        prestamoCrearRequest.setIdentificacionUsuario("123456789");

        prestamoRestController.crear(prestamoCrearRequest);

        String prestamoJson = "{\"isbn\":\"XASD897\",\"identificacionUsuario\":\"123456789\",\"tipoUsuario\":3}";
        MvcResult result = mockMvc.perform(post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(prestamoJson))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        String mensajeEsperado = "El usuario con identificaciÃ³n 123456789 ya tiene un libro prestado por lo cual no se le puede realizar otro prÃ©stamo";
        assertTrue(jsonResponse.has("mensaje"));
        assertEquals(mensajeEsperado, jsonResponse.get("mensaje").asText());

    }

    @Test
    public void testTipoUsuarioInvalido() throws Exception {

        String prestamoJson = "{\"isbn\":\"XASD897\",\"identificacionUsuario\":\"123456789\",\"tipoUsuario\":9}";
        MvcResult result = mockMvc.perform(post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(prestamoJson))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        String mensajeEsperado = "Tipo de usuario no permitido en la biblioteca";
        assertTrue(jsonResponse.has("mensaje"));
        assertEquals(mensajeEsperado, jsonResponse.get("mensaje").asText());

    }

    @Test
    public void testNoExistenPrestamos() throws Exception {

        MvcResult result = mockMvc.perform(get("/prestamo"))
                .andExpect(status().isNotFound()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        String mensajeEsperado = "No existen prestamos registrados";
        assertTrue(jsonResponse.has("mensaje"));
        assertEquals(mensajeEsperado, jsonResponse.get("mensaje").asText());

    }

    @Test
    public void testNoExistenPrestamo() throws Exception {

        MvcResult result = mockMvc.perform(get("/prestamo/9999"))
                .andExpect(status().isNotFound()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        String mensajeEsperado = "No existe el prestamo";
        assertTrue(jsonResponse.has("mensaje"));
        assertEquals(mensajeEsperado, jsonResponse.get("mensaje").asText());

    }

    @Test
    public void testCrearPrestamoSinDatos() throws Exception {

        String prestamoJson = "{}";
        MvcResult result = mockMvc.perform(post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(prestamoJson))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(result.getResponse().getContentAsString());

        assertTrue(jsonResponse.has("isbn"));
        assertEquals("No puede estar vacio", jsonResponse.get("isbn").asText());
        assertTrue(jsonResponse.has("tipoUsuario"));
        assertEquals("Es requerido", jsonResponse.get("tipoUsuario").asText());
        assertTrue(jsonResponse.has("identificacionUsuario"));
        assertEquals("No puede estar vacio", jsonResponse.get("identificacionUsuario").asText());

    }

}
