package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DatabaseConnection.DatabaseConnection;
import com.model.users;
import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginForm")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter writer=resp.getWriter();
		
		String LoginEmail=req.getParameter("LoginEmail");
				String LoginPassword= req.getParameter("LoginPassword");
		
		try {
			
		Connection con = DatabaseConnection.getConnection()	;
		
		String sqlQuery=" select * from registration where Email=? AND Password =?";
		PreparedStatement ps= con.prepareStatement(sqlQuery);
		
		ps.setString(1,LoginEmail);
		ps.setString(2,LoginPassword);
	ResultSet rs =ps.executeQuery();
	
	if(rs.next()) {
		
		users  user = new users();
		
		user.setFullName(rs.getString("FullName"));
		user.setEmail(rs.getString("Email"));
		user.setAddress(rs.getString("Address"));
		
		HttpSession session = req.getSession();
		session.setAttribute("session_user", user);
		
		writer.println("<h3 style='color:green'>Login successfully </h3>");
		RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
		rd.forward(req, resp);
		
		
		
	}
	else {
		
		resp.setContentType("text/html");
		writer.println(" <h3 style='color:red'>Email and Password Didn't Match</h3>"); 
		
		RequestDispatcher rd = req.getRequestDispatcher("/login.html");
		rd.include(req, resp);
		
		
	}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
