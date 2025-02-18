package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }


    //multiplication tests
    @Test
    public void testMultiplication() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "*")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11110"));
    }

    @Test
    public void testMultiplicationWithZero() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "0")
                .param("operator", "*")
                .param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void testMultiplicationWithOne() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "1")
                .param("operator", "*")
                .param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "101"));
    }

    @Test
    public void testMultiplicationWithDifferentLengths() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "10")
                .param("operator", "*")
                .param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10010"));
    }

    @Test
    public void testMultiplicationWithLargeNumbers() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "1111")
                .param("operator", "*")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10010110"));
    }

    //AND tests
    @Test
    public void testBitwiseAND() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "&")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100"));
    }

    @Test
    public void testBitwiseANDWithZeros() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "000")
                .param("operator", "&")
                .param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void testBitwiseANDWithOne() throws Exception {
    this.mvc.perform(post("/")
            .param("operand1", "1")
            .param("operator", "&")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1"));
    }

    @Test
    public void testBitwiseANDWithDifferentLengths() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "1101")
                .param("operator", "&")
                .param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "101"));
    }

    @Test
    public void testBitwiseANDWithLargeNumbers() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "11110000")
                .param("operator", "&")
                .param("operand2", "10101010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10100000"));
    }

    //OR tests
    @Test
    public void testBitwiseOR() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "|")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111"));
    }

    @Test
    public void testBitwiseORWithZeros() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "000")
                .param("operator", "|")
                .param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "101"));
    }

    @Test
    public void testBitwiseORWithOne() throws Exception {
    this.mvc.perform(post("/")
            .param("operand1", "001")
            .param("operator", "|")
            .param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "101"));
    }

    @Test
    public void testBitwiseORWithDifferentLengths() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "1101")
                .param("operator", "|")
                .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void testBitwiseORWithLargeNumbers() throws Exception {
        this.mvc.perform(post("/")
                .param("operand1", "11110000")
                .param("operator", "|")
                .param("operand2", "00001111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11111111"));
    }
}
