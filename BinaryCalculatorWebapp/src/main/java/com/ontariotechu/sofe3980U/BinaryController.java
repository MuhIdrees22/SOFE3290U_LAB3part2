package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BinaryController {

	@GetMapping("/")
	public String getCalculator(@RequestParam(name="operand1", required=false, defaultValue="") String operand1, Model model) {
		model.addAttribute("operand1", operand1);
		model.addAttribute("operand1Focused", operand1.length()>0);
        return "calculator";
	}

	@PostMapping("/")
	public String getResult(@RequestParam String operand1, @RequestParam String operator, @RequestParam String operand2, Model model) {
    Binary binary1 = new Binary(operand1);
    Binary binary2 = new Binary(operand2);
    Binary result;

    switch (operator) {
        case "+":
            result = Binary.add(binary1, binary2);
            break;
        case "*":
            result = Binary.multiply(binary1, binary2);
            break;
        case "&":
            result = Binary.and(binary1, binary2);
            break;
        case "|":
            result = Binary.or(binary1, binary2);
            break;
        default:
            return "error"; // Handle invalid operators
    }

    model.addAttribute("operand1", operand1);
    model.addAttribute("operator", operator);
    model.addAttribute("operand2", operand2);
    model.addAttribute("result", result.getValue());
    return "result";
}

}