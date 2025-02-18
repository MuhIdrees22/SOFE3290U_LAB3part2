package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

	@GetMapping("/add")
	public String addString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		Binary number1=new Binary (operand1);
		Binary number2=new Binary (operand2);
        return  Binary.add(number1,number2).getValue();
		// http://localhost:8080/add?operand1=111&operand2=1010
	}

	@GetMapping("/multiply")
	public String multiply(@RequestParam String operand1, @RequestParam String operand2) {
		Binary binary1 = new Binary(operand1);
		Binary binary2 = new Binary(operand2);
		return Binary.multiply(binary1, binary2).getValue();
	}

	@GetMapping("/and")
	public String and(@RequestParam String operand1, @RequestParam String operand2) {
		Binary binary1 = new Binary(operand1);
		Binary binary2 = new Binary(operand2);
		return Binary.and(binary1, binary2).getValue();
	}

	@GetMapping("/or")
	public String or(@RequestParam String operand1, @RequestParam String operand2) {
		Binary binary1 = new Binary(operand1);
		Binary binary2 = new Binary(operand2);
		return Binary.or(binary1, binary2).getValue();
	}

	
	@GetMapping("/add_json")
	public BinaryAPIResult addJSON(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		Binary number1=new Binary (operand1);
		Binary number2=new Binary (operand2);
        return  new BinaryAPIResult(number1,"add",number2,Binary.add(number1,number2));
		// http://localhost:8080/add?operand1=111&operand2=1010
	}

}