package jp.co.example.ecommerce_c.form;

import java.util.List;

/**
 * リクエストパラメータで受け取ったピザのフォーム.
 * 
 * @author koichi.nagata
 *
 */
public class OrderItemForm {
	/** 注文するピザID */
	private Integer itemId;
	/** ピザの数量 */
	private Integer quantity;
	/** ピザのサイズ */
	private String size;
	/** 乗せるトッピングのリスト */
	private List<Integer> orderToppingList;

	public final Integer getItemId() {
		return itemId;
	}

	public final void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public final Integer getQuantity() {
		return quantity;
	}

	public final void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public final String getSize() {
		return size;
	}

	public final void setSize(String size) {
		this.size = size;
	}

	public final List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public final void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", orderToppingList="
				+ orderToppingList + "]";
	}

}
