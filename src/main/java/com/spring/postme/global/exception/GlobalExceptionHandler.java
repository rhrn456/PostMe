package com.spring.postme.global.exception;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	// 어떤 예외인지 노출시키고 싶지 않을시 exceptionMessage값 수정

	// 예외: NullPointerException
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e, Model model) {
		model.addAttribute("exceptionMessage", "NullPointerException 이 발생했습니다.");
		return "error";
	}

	// 예외: IllegalArgumentException
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
		model.addAttribute("exceptionMessage", "IllegalArgumentException 이 발생했습니다.");
		return "error";
	}

	// 예외: FileNotFoundException (파일을 찾을 수 없는 경우)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(FileNotFoundException.class)
	public String handleFileNotFoundException(FileNotFoundException e, Model model) {
		model.addAttribute("exceptionMessage", "파일을 찾을 수 없습니다.");
		return "error";
	}

	// 예외: PersistenceException (mybatis 예외)
	@ExceptionHandler(PersistenceException.class)
	public String handlePersistenceException(PersistenceException e, Model model) {
		model.addAttribute("exceptionMessage", "MyBatis 예외가 발생했습니다.");
		return "error";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(SQLException e, Model model) {
		model.addAttribute("exceptionMessage", "SQL에서 예외가 발생하였습니다.");
		return "error";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BadSqlGrammarException.class)
	public String handleBadSqlGrammarException(BadSqlGrammarException e, Model model) {
		model.addAttribute("exceptionMessage", "SQL문에서 오류가 발생하였습니다.");
		return "error";
	}

	// 모든 예외
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String postExceptionHandler(Exception e, Model model) {
		model.addAttribute("exceptionMessage", "예외가 발생하였습니다.");
		System.out.println(e);
		System.out.println(model);
		return "error";
	}

}
