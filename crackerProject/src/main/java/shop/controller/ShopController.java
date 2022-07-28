package shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import crackeremail.bean.CrackeremailDTO;
import member.bean.MemberDTO;
import product.bean.ProductbuylistDTO;
import shop.bean.MemberbuylistDTO;
import shop.service.ShopService;



@Controller
public class ShopController {
	
	@Autowired
	private ShopService shopService;

	@GetMapping("/shop/shopmain")
	public ModelAndView shopmain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/shop/shopmain");
		return mav;
	}
	
	@GetMapping("/shop/shopitem")
	public ModelAndView shopitem() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/shop/shopitem");
		return mav;
	}
	
	
	@PostMapping("/shop/getProductBuy")
	@ResponseBody
	public CrackeremailDTO getProductBuy(@RequestParam String memEmail) {
		CrackeremailDTO crackeremailDTO = shopService.getProductBuy(memEmail);
//		System.out.println(crackeremailDTO);
		return crackeremailDTO;
	}
	
	@PostMapping("/shop/memberaddressUpdate")
	@ResponseBody
	public void memberaddressUpdate(@RequestParam Map<String,String>map) {
		shopService.memberaddressUpdate(map);
	}
	
	@GetMapping("/shop/shopMypage")
	public ModelAndView shopMypage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	
	@GetMapping("/shop/shoporder")
	public ModelAndView shoporder() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude","/WEB-INF/shop/shoporder.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	

	@GetMapping("/shop/cartView")
	public ModelAndView cartView() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude","/WEB-INF/shop/cartView.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}

	@GetMapping("/shop/shopCheckout")
	public ModelAndView shopCheckout() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/shop/shopCheckout");
		return mav;
	}
	
	@PostMapping("/shop/shopPayForm")
	@ResponseBody
	public Map<String,Object> shopPay(@RequestParam Map<String,String>map, HttpSession session) {
		System.out.println("들어오는거 확인 : " + map);
		Map<String,Object>map1 = shopService.shopPay(map);
		map1.put("shopqty", map.get("shopqty"));
		map1.put("totalprice",map.get("totalprice"));
		map1.put("day", map.get("day")); 
		session.setAttribute("shoppay", map1);
		return map1;
	}
	
	@GetMapping("/shop/shopproductbuy")
	public ModelAndView shopshopproductbuy() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude","/WEB-INF/shop/shopproductbuy.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	@GetMapping("/shop/shopPay")
	public ModelAndView shopPay(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		session.getAttribute("shoppay");
		mav.addObject("item",session.getAttribute("shoppay"));
		
		System.out.println("어떻게 들어오나 확인"+session.getAttribute("shoppay"));
		mav.addObject("MyPageInclude", "/WEB-INF/shop/shopPay.jsp");
		mav.setViewName("/shop/shopMypage");

		return mav;
	}
	
	//결제되었을때
	@PostMapping("/shop/memberBuyList")
	@ResponseBody
	public void memberBuyList(@RequestParam Map<String,String>map) {
		shopService.memberBuyList(map);
		System.out.println(map);
	}
	
	@PostMapping("/shop/memberBuyList2")
	@ResponseBody
	public void memberBuyList2(@RequestParam Map<String,String>map) {
		shopService.memberBuyList2(map);
		System.out.println(map);
	}
	//장바구니
	@PostMapping("/shop/shopcartForm")
	@ResponseBody
	public Map<String, Object> shopcartForm(@RequestParam Map<String,String>map){
		System.out.println("장바구니 넘어오는값 : "+map);
		Map<String,Object>map1 = shopService.shopcartForm(map);
		System.out.println(map1);
		return map1;
	}
	//장바구니
	@PostMapping("/shop/getcartView")
	@ResponseBody
	public List<ProductbuylistDTO> getcartView(@RequestParam String memberEmail) {
		List<ProductbuylistDTO> list = shopService.getcartView(memberEmail);
		System.out.println( "확인 : "+list);
		return list;
	}
	@PostMapping("/shop/cartViewDelete")
	@ResponseBody
	public void cartViewDelete(@RequestParam String productseq,HttpSession session) {
		String memEmail = (String)session.getAttribute("memEmail");
		Map<String,String>map = new HashMap<String,String>();
		map.put("productseq", productseq);
		map.put("memberEmail", memEmail);
		shopService.cartViewDelete(map);
	}
	
	@GetMapping("/shop/shopmemberpasswordcheckForm")
	public ModelAndView shopmemberpasswordcheckForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude", "/WEB-INF/member/memberUpdateForm.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	@GetMapping("/shop/customerWriteForm")
	public ModelAndView customerWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude", "/WEB-INF/customer/customerWriteForm.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	@PostMapping("/shop/shopCartPay")
	@ResponseBody
	public Map<String,Object> shopCartPay(@RequestParam Map<String,Object>map,HttpSession session) {
		System.out.println("들어오는 확인 : " + map);
		String jsonDate = map.get("list").toString();
		/*
		 * JSONParser parser = new JSONParser(); Object obj = null; try { obj =
		 * parser.parse(jsonDate); } catch (ParseException e) { e.printStackTrace(); }
		 */
		map.put("memberemail", session.getAttribute("memEmail"));
		map.put("list", jsonDate);
		Map<String,Object>map1 = shopService.shopCartPay(map);
		session.setAttribute("item", map1);
		return map1;
	}
	
	@GetMapping("/shop/shopCartBuyPay")
	public ModelAndView shopCartBuyPay(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println("item =" + session.getAttribute("item"));
		mav.addObject("item",session.getAttribute("item"));
		mav.addObject("MyPageInclude", "/WEB-INF/shop/shopCartBuyPay.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	
	@GetMapping("/shop/buylist")
	public ModelAndView buylist() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("MyPageInclude", "/WEB-INF/shop/buylist.jsp");
		mav.setViewName("/shop/shopMypage");
		return mav;
	}
	@PostMapping("/shop/getbuylist")
	@ResponseBody
	public List<MemberbuylistDTO> getbuylist(@RequestParam String memberemail) {
		return shopService.getbuylist(memberemail);
	}
	
}	

	
