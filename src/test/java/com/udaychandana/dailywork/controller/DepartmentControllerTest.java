package com.udaychandana.dailywork.controller;

import com.udaychandana.dailywork.entity.Department;
import com.udaychandana.dailywork.error.DepartmentNotFoundException;
import com.udaychandana.dailywork.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp(){

        department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentId(1L)
                .departmentCode("IT-06")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {

        Department departmentInput = Department.builder()
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentService.saveDepartment(departmentInput))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"departmentName\":\"IT\",\n" +
                "    \"departmentAddress\":\"Banglore\",\n" +
                "    \"departmentCode\":\"IT-06\"\n" +
                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {

        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.departmentName")
                .value(department.getDepartmentName()));
    }
}