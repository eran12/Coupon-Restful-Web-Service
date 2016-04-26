package com.ee.UserActions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ee.Base.UserBase;
import com.ee.Base.UserType;
import com.ee.Facade.AdminFacade;
import com.ee.Facade.UserFacade;

@Path("/admin")
public class AdminService extends UserBaseService{

	@GET
	@Path("/getUserByName/{userName}/{userType}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserBase getUserByName(@PathParam("userName")String userName,@PathParam("userType") UserType userType, @Context HttpServletRequest request){
		AdminFacade user = (AdminFacade) getFacadeSession(request);
		System.out.println(user.getUserByName(userName, userType));
		return user.getUserByName(userName, userType);
	}

	@GET
	@Path("/getUserById")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBase getUserById( @Context HttpServletRequest request) {
		UserFacade user = getFacadeSession(request);
		return user.getUserById(user.getUser().getId());
	}

}
