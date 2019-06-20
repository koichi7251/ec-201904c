package jp.co.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.service.DisplayItemListService;

/**
 * 商品関連機能の処理の制御を行うコントローラー.
 * 
 * @author keita.tomooka
 *
 */
@Controller
@RequestMapping("/displayItemList")
public class DisplayItemListController {
	@Autowired
	DisplayItemListService service;

	/**
	 * 商品一覧画面にフォワードする処理を行う.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品リストの画面「item_list.html」
	 */
	@RequestMapping("/showList")
	public String service(Model model) {
		List<Item> itemList = service.findAll();
		List<Item> item3List = new ArrayList<Item>();
		List<List<Item>> item3Lists= new ArrayList<>();
		for(int i=0;i<itemList.size();i++) {
			item3List.add(itemList.get(i));
			if(item3List.size()==3) {
				item3Lists.add(item3List);
				item3List=new ArrayList<>();
			}
		}
		System.out.println(item3Lists);
		model.addAttribute("item3Lists", item3Lists);
		return "item_list";
	}

}
