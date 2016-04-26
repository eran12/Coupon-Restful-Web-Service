package com.ee.UserActions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.ee.Base.Coupon;
import com.ee.Facade.CustomerFacade;

@Path("/customer")
public class CustomerService extends UserBaseService{

	public CustomerService() {
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. 
	 * @return Collection with all user coupons. the collection return in JSON format.
	 */
	@GET
	@Path("/getAllUserCoupons/")
	public Collection<Coupon> getAllUserCoupons(@Context HttpServletRequest request) {
		System.out.println("UserBaseService.getAllUserCoupons()");
		CustomerFacade user = (CustomerFacade) getFacadeSession(request);
		return user.getAllUserCoupons();
	}

}
