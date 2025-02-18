package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void addJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


    // Multiplication tests
    @Test
    public void testMultiplicationAPI() throws Exception {
        this.mvc.perform(get("/multiply")
            .param("operand1", "101")
            .param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(content().string("11110"));
    }

    @Test
    public void testMultiplicationWithZeroAPI() throws Exception {
        this.mvc.perform(get("/multiply")
            .param("operand1", "0")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void testMultiplicationWithOneAPI() throws Exception {
        this.mvc.perform(get("/multiply")
            .param("operand1", "1")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("101"));
    }

    @Test
    public void testMultiplicationWithDifferentLengthsAPI() throws Exception {
        this.mvc.perform(get("/multiply")
            .param("operand1", "10")
            .param("operand2", "1001"))
            .andExpect(status().isOk())
            .andExpect(content().string("10010"));
    }

    @Test
    public void testMultiplicationWithLargeNumbersAPI() throws Exception {
        this.mvc.perform(get("/multiply")
            .param("operand1", "1111")
            .param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10010110"));
    }

    // AND tests
    @Test
    public void testBitwiseANDAPI() throws Exception {
        this.mvc.perform(get("/and")
            .param("operand1", "101")
            .param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(content().string("100"));
    }

    @Test
    public void testBitwiseANDWithZerosAPI() throws Exception {
        this.mvc.perform(get("/and")
            .param("operand1", "000")
            .param("operand2", "111"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void testBitwiseANDWithOneAPI() throws Exception {
    this.mvc.perform(get("/and")
            .param("operand1", "1")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
    }

    @Test
    public void testBitwiseANDWithDifferentLengthsAPI() throws Exception {
        this.mvc.perform(get("/and")
            .param("operand1", "1101")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("101"));
    }

    @Test
    public void testBitwiseANDWithLargeNumbersAPI() throws Exception {
        this.mvc.perform(get("/and")
            .param("operand1", "11110000")
            .param("operand2", "10101010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10100000"));
    }

    // OR tests
    @Test
    public void testBitwiseORAPI() throws Exception {
        this.mvc.perform(get("/or")
                .param("operand1", "101")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("111"));
    }

    @Test
    public void testBitwiseORWithZerosAPI() throws Exception {
        this.mvc.perform(get("/or")
            .param("operand1", "000")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("101"));
    }

    @Test
    public void testBitwiseORWithOneAPI() throws Exception {
    this.mvc.perform(get("/or")
            .param("operand1", "001")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("101"));
    }

    @Test
    public void testBitwiseORWithDifferentLengthsAPI() throws Exception {
        this.mvc.perform(get("/or")
            .param("operand1", "1101")
            .param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    @Test
    public void testBitwiseORWithLargeNumbersAPI() throws Exception {
        this.mvc.perform(get("/or")
            .param("operand1", "11110000")
            .param("operand2", "00001111"))
            .andExpect(status().isOk())
            .andExpect(content().string("11111111"));
    }
}