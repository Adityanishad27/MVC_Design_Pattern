package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.DatabaseConnection.DatabaseConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerForm")
public class Register extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String FullName =req.getParameter("FullName");
		String Email=req.getParameter("Email");
		String Password = req.getParameter("Password");
		String Address = req.getParameter("Address");
		
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		
		
		try {
			
			Connection con = DatabaseConnection.getConnection();
			String Query ="Insert Into registration values(?,?,?,?)";
			
			PreparedStatement ps= con.prepareStatement(Query);
			ps.setString(1, FullName);
			ps.setString(2, Email);
			ps.setString(3, Password);
			ps.setString(4, Address);
			
		int count=	ps.executeUpdate();
		
		if(count > 0) {
			resp.setContentType("text/html");
			writer.println("<h3 style='color:green'>Registered successfully </h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req, resp);
		}
		else {
			resp.setContentType("text/html");
			writer.println("<h3 style='color:red'> Not Registerd </h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/Register.html");
			rd.include(req, resp);
		}
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
