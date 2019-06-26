package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Topping;
import jp.co.example.ecommerce_c.repository.ItemRepository;
import jp.co.example.ecommerce_c.repository.OrderItemRepository;
import jp.co.example.ecommerce_c.repository.OrderRepository;
import jp.co.example.ecommerce_c.repository.OrderToppingRepository;
import jp.co.example.ecommerce_c.repository.ToppingRepository;

/**
 * 注文履歴を取得するためのサービス.
 * 
 * @author takara.miyazaki
 *
 */
@Service
@Transactional
public class HistoryService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 指定したユーザIDの注文履歴のリストを取得.
	 * 
	 * @param userId ユーザID
	 * @return 注文履歴のリスト 存在しなければnullを返す
	 */
	public List<Order> getOrderList(int userId){
		List<Order> orderList = orderRepository.findAllOrderByUserId(userId);
		if(orderList != null) {
			for(Order order : orderList) {
				setOrderItemListToOrder(order);
			}
		}
		return orderList;
	}
	
	/**
	 * 指定したオーダーIDの注文内容を取得.
	 *
	 * @param orderId 注文ID
	 * @return Orderオブジェクト
	 */
	public Order getOrder(int orderId) {
		Order order = orderRepository.findById(orderId);
		setOrderItemListToOrder(order);
		return order;
	}

	private void setOrderItemListToOrder(Order order) {
		List<OrderItem> orderItemList = orderItemRepository.findById(order.getId());
		order.setOrderItemList(orderItemList);
		for (OrderItem orderItem : orderItemList) {
			setItemToOrderItem(orderItem);
			setOrderToppingListToOrderItem(orderItem);
		}
	}

	private void setOrderToppingListToOrderItem(OrderItem orderItem) {
		List<OrderTopping> orderToppingList = orderToppingRepository.findById(orderItem.getId());
		orderItem.setOrderToppingList(orderToppingList);
		for (OrderTopping orderTopping : orderToppingList) {
			setToppingToOrderTopping(orderTopping);
		}
	}

	private void setToppingToOrderTopping(OrderTopping orderTopping) {
		Topping topping = toppingRepository.load(orderTopping.getToppingId());
		orderTopping.setTopping(topping);
	}

	private void setItemToOrderItem(OrderItem orderItem) {
		Item item = itemRepository.load(orderItem.getItemId());
		orderItem.setItem(item);
	}

}
