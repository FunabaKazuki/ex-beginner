package com.example.contorller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/exam6")
public class ShoppingCartController {
	@Autowired
	private ServletContext application;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String index(Model model) {
		Item item1=new Item("手帳ノート", 1000);
		Item item2=new Item("文房具セット", 1500);
		Item item3=new Item("ファイル", 2000);
		Map<Integer,Item> itemMap = new LinkedHashMap<>();
		itemMap.put(1, item1);
		itemMap.put(2, item2);
		itemMap.put(3, item3);
		application.setAttribute("itemMap", itemMap);
		
		if(session.getAttribute("cartList")==null) {
			List<Item> cartList = new ArrayList<>();
			session.setAttribute("cartList", cartList);
		}
		
		System.out.println(session.getAttribute("cartList"));
		Integer totalPrice=0;
		for(Item item : (List<Item>)session.getAttribute("cartList")) {
			
			if(item!=null) {	
			totalPrice+=item.getPrice();
			}
			
		}
		model.addAttribute("totalPrice", totalPrice);
		System.out.println(totalPrice);
		
		return "item-and-cart";
	}
	
	@RequestMapping("/incart")
	public String inCart(Integer itemNum,Model model) {
		System.out.println("送信された商品番号"+itemNum);
		List<Item> cartList=(List<Item>)session.getAttribute("cartList"); 
		Map<Integer,Item> itemMap=(Map<Integer,Item>)application.getAttribute("itemMap");
		cartList.add(itemMap.get(itemNum));
		session.setAttribute("cartList", cartList);

		return index(model);
	}
	
	@RequestMapping("/delete")
	public String delete(Integer itemNum , Model model) {
		System.out.println("削除品番号"+itemNum);
		List<Item> cartList=(List<Item>)session.getAttribute("cartList"); 
		Map<Integer,Item> itemMap=(Map<Integer,Item>)application.getAttribute("itemMap");
		cartList.remove(itemMap.get(itemNum));
		session.setAttribute("cartList", cartList);
		
		return index(model);
	}
	
	
	
}
