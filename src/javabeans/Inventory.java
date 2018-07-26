package javabeans;

public class Inventory {
	private int wrhs_no;
	private int goods_no;
	private int amount;
	
	private String wrhs_name;
	private String goods_name;
	
	private float goods_price;
	private float total;
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getWrhs_no() {
		return wrhs_no;
	}

	public void setWrhs_no(int wrhs_no) {
		this.wrhs_no = wrhs_no;
	}

	public int getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(int goods_no) {
		this.goods_no = goods_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getWrhs_name() {
		return wrhs_name;
	}

	public void setWrhs_name(String wrhs_name) {
		this.wrhs_name = wrhs_name;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}

}
