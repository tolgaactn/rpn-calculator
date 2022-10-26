package com.example.rpn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class CalculatorController {
	
	 @PostMapping("/calculate")
	    public HashMap<String,Integer> calculate(@RequestBody RequestEntity requestEntity) {
	        String[] requestArray = requestEntity.getExpression().split("\\s+");
	        Stack<Integer> stack = new Stack<Integer>();
	        Arrays.stream(requestArray).forEach(reqItem -> {
	            try{
	                stack.push(Integer.parseInt(reqItem));
	            }catch (Exception exception){
	                Integer x;
	                Integer y;
	                switch (reqItem) {
	                    case "+":
	                        x = (Integer) stack.pop();
	                        y = (Integer) stack.pop();
	                        stack.push(y+x);
	                        break;
	                    case "-":
	                        x = (Integer) stack.pop();
	                        y = (Integer) stack.pop();
	                        stack.push(y-x);
	                        break;
	                    case "*":
	                        x = (Integer) stack.pop();
	                        y = (Integer) stack.pop();
	                        stack.push(y*x);
	                        break;
	                    case "/":
	                        x = (Integer) stack.pop();
	                        y = (Integer) stack.pop();
	                        stack.push(y/x);
	                        break;
	                }
	            }
	        });
	        HashMap<String,Integer> response = new HashMap<>();
	        response.put("result",stack.pop());
	        return response;
	    }
	
}
