package shop.dao;

import java.util.List;
import java.util.Map;

import crackeremail.bean.CrackeremailDTO;
import member.bean.MemberDTO;
import product.bean.ProductbuylistDTO;

public interface ShopDAO {

	public CrackeremailDTO getProductBuy(String memEmail);

	public void memberaddressUpdate(Map<String, String> map);

	public Map<String,Object> shopPay(Map<String, String> map);

	public Map<String, Object> shopcartForm(Map<String, String> map);

	public List<ProductbuylistDTO> getcartView(String memberEmail);

	public void cartViewDelete(Map<String, String> map);

	public Map<String, Object> shopCartPay(Map<String, Object> map);

}
