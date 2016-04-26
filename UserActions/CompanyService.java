package com.ee.UserActions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ee.Base.Coupon;
import com.ee.Facade.CompanyFacade;

@Path("/company")
public class CompanyService extends UserBaseService{

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. 
	 * @return Collection with all user coupons. the collection return in JSON format.
	 */
	@GET
	@Path("/getAllUserCoupons/")
	public Collection<Coupon> getAllUserCoupons(@Context HttpServletRequest request) {
		System.out.println("UserBaseService.getAllUserCoupons()");
		CompanyFacade user = (CompanyFacade) getFacadeSession(request);
		return user.getAllUserCoupons();
	}


	@GET
	@Path("/createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCoupon(Coupon coupon, @Context HttpServletRequest request) {
		CompanyFacade facade = (CompanyFacade) getFacadeSession(request);
		facade.createCoupon(coupon);
	}

	@DELETE
	@Path("/removeCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeCoupon(Coupon coupon, @Context HttpServletRequest request) {
		CompanyFacade facade = (CompanyFacade) getFacadeSession(request);	
		facade.removeCoupon(coupon);
	}

	@GET
	@Path("/upDateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void upDateCoupon(Coupon coupon, @Context HttpServletRequest request) {
		CompanyFacade facade = (CompanyFacade) getFacadeSession(request);	
		facade.upDateCoupon(coupon);
	}

}
