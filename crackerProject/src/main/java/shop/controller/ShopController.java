package shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import crackeremail.bean.CrackeremailDTO;
import member.bean.MemberDTO;
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
		mav.setViewName("/shop/cartView");
		return mav;
	}
	@PostMapping("/shop/shopPayForm")
	@ResponseBody
	public Map<String,Object> shopPay(@RequestParam Map<String,String>map, HttpSession session) {
		Map<String,Object>map1 = shopService.shopPay(map);
		map1.put("shopqty", map.get("shopqty"));
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
		mav.setViewName("/shop/shopPay");
		return mav;
	}
}
