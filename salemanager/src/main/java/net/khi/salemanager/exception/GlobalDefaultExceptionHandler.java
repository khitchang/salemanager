package net.khi.salemanager.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerExceptionFound(){
		
		ModelAndView mv = new ModelAndView("error");
		
			mv.addObject("errorTitle", "The page is not constructed!! ");
			mv.addObject("errorDescription", "The page you are looking for is not available now");
			mv.addObject("title", "404 error Page");	
			 
		
		return mv;
		
	}
	
	
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNoFoundException(){
		
		ModelAndView mv = new ModelAndView("error");
		
			mv.addObject("errorTitle", "This product is not available!! ");
			mv.addObject("errorDescription", "The product you are looking for is not available rigth now");
			mv.addObject("title", "Product Unavailable");	
			 
		
		return mv;
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex){
		
		ModelAndView mv = new ModelAndView("error");
		
			mv.addObject("errorTitle", "Contact Your Administrator");
			
			/* only for debugging your application */
			StringWriter sw = new StringWriter() ;
			PrintWriter pw = new PrintWriter(sw);
			
			ex.printStackTrace(pw);
			
			mv.addObject("errorDescription", sw.toString());
			mv.addObject("title", "error");	
			 
		
		return mv;
		
	}
	
	
	
	
	
	
	

}
