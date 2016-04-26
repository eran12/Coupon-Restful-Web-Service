package com.ee.UserActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ee.Base.Coupon;
import com.ee.Base.CouponType;
import com.ee.Base.UserBase;
import com.ee.Facade.UserFacade;

@Produces(MediaType.APPLICATION_JSON)
public class UserBaseService {

	/**
	 * this method is for the service use.
	 * the method will get the facade attribute from the {@link HttpSession} and return it.</br>
	 * the method is exclude from the web methods.
	 * @param request {@link HttpServletRequest}
	 * @return facade attribute from the session.
	 */
	@WebMethod(exclude = true)
	protected UserFacade getFacadeSession(@Context HttpServletRequest request){
		HttpSession session = request.getSession();
		UserFacade facade = (UserFacade) session.getAttribute("facade");
		return facade;
	}


	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. 
	 * @param id long. the coupon id.
	 * @return Coupon object by the given id. the object return in JSON format.
	 */
	@GET
	@Path("/getCouponById/{couponId}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Coupon getCouponById(@PathParam("couponId")long id, @Context HttpServletRequest request) {
		UserFacade facade = getFacadeSession(request);
		Coupon coupon = facade.getCouponById(id);
		if(coupon.getTitle() != null){
			return coupon;
		}else{
			return null;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. 
	 * @return Collection with all coupons in the DB. the collection return in JSON format.
	 */
	@GET
	@Path("/getAllCoupons/all")
	public Collection<Coupon> getAllCoupons(@Context HttpServletRequest request) {
		System.out.println("UserBasic.getAllCoupons()");
		UserFacade facade = getFacadeSession(request);
		System.out.println(facade.toString());
		return facade.getAllCoupons();
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. 
	 * @param id long. the company id.
	 * @return Collection with all the company coupons. the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponsByCompany/{companyId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCompany(@PathParam("companyId") long id, @Context HttpServletRequest request) {
		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsByCompany(id);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. </br>
	 * <strong>send format from HTML dd-mm-yyyy</strong>
	 * @param minStartDate String. in the format <strong>dd-mm-yyyy</strong>.
	 * @return Collection with all the coupons that the given date is the start date and above.</br>
	 * the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponsByMinStartDate/{minStartDate}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByMinStartDate(@PathParam("minStartDate")String minStartDate, @Context HttpServletRequest request) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(minStartDate, formatter);

		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsByMinStartDate(date);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. </br>
	 * <strong>send format from HTML dd-mm-yyyy</strong>
	 * @param maxEndDate String. in the format <strong>dd-mm-yyyy</strong>.
	 * @return Collection with all the coupons that the given date is the end date and below.</br>
	 * the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponsByMaxEndDate/{maxEndDate}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByMaxEndDate(@PathParam("maxEndDate")String maxEndDate, @Context HttpServletRequest request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(maxEndDate, formatter);
		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsByMaxEndDate(date);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. </br>
	 * @param maxPrice double.
	 * @return Collection with all the coupons with the given price is the max and below.</br>
	 * the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponsByMaxPrice/{maxPrice}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByMaxPrice(@PathParam("maxPrice")double maxPrice, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsByMaxPrice(maxPrice);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. </br>
	 * @param couponType {@link CouponType}. 
	 * @return Collection with all the coupons with the given {@link CouponType}.</br>
	 * the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponsByType/{couponType}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByType(@PathParam("couponType")CouponType couponType, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsByType(couponType);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

	/**
	 * the method will get the facade attribute from the session by the {@link #getFacadeSession(HttpServletRequest)} method</br>
	 * and uses it with the functions inside the user facade. </br>
	 * @param couponTitle String.
	 * @return Collection with all the coupons that have the title like the given String.</br>
	 * the collection return in JSON format.
	 */
	@GET
	@Path("/getCouponByTitle/{couponTitle}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Coupon getCouponByTitle(@PathParam("couponTitle")String couponTitle, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		return facade.getCouponByTitle(couponTitle);
	}

	@POST
	@Path("setUserPassword/{email}/{oldPassword}/{newPassword}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setUserPassword(@PathParam("email")String email,@PathParam("oldPassword") String oldPassword,@PathParam("newPassword") String newPassword, @Context HttpServletRequest request){
		UserFacade facade = getFacadeSession(request);
		facade.setUserPassword(email, oldPassword, newPassword);
	}

	@GET
	@Path("/upDateUser/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void upDateUser(UserBase User, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		facade.upDateUser();
	}

	@GET
	@Path("/getUserEmail/")
	public String getUserEmail(@Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		return facade.getUserEmail();
	}

	@POST
	@Path("setUserEmail/{userEmail}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setUserEmail(@PathParam("userEmail")String email, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		facade.setUserEmail(email);
	}

	@POST
	@Path("/getUserPassword/{userName}/{userEmail}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserPassword(@PathParam("userName")String userName,@PathParam("userEmail") String userEmail, @Context HttpServletRequest request)  {
		UserFacade facade = getFacadeSession(request);
		return facade.getUserPassword(userName, userEmail);
	}

	@GET
	@Path("/getCouponsLikeTitle/{title}")
	public Collection<Coupon> getCouponsLikeTitle(@PathParam("title")String title, @Context HttpServletRequest request){
		UserFacade facade = getFacadeSession(request);
		Collection<Coupon> coupons = facade.getCouponsLikeTitle(title);
		if(coupons.isEmpty()){
			return null;
		}
		else{
			return coupons;
		}
	}

}
