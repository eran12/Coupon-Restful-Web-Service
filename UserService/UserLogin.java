package com.ee.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ee.Base.UserType;
import com.ee.Exception.UnKnownUserTypeException;
import com.ee.Facade.UserFacade;
import com.ee.System.CouponSystem;

@Path("/enternce")
public class UserLogin {



	@GET
	@Path("/userlogin/{userName}/{password}/{userType}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(@PathParam("userName") String userName,@PathParam("password") String password,@PathParam("userType") UserType userType,
			@Context HttpServletRequest request, @Context HttpServletResponse response){

		System.out.println("UserLogin.login()");
		HttpSession session = request.getSession(true);
		UserFacade facade = null;
		switch(userType){

		case ADMIN:
			facade =  CouponSystem.getInstance().login(userName, password, UserType.ADMIN);
			break;
		case COMPANY:
			facade =  CouponSystem.getInstance().login(userName, password, UserType.COMPANY);
			break;
		case CUSTOMER:
			facade =  CouponSystem.getInstance().login(userName, password, UserType.CUSTOMER);
			break;
		default:
			throw new UnKnownUserTypeException("the user type is unknown");
		}

		session.setAttribute("facade", facade);
		System.out.println(facade);
		try {
			request.getRequestDispatcher("/UserCookie").include(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("login: " + facade);
		return facade.toString();

	}

}
