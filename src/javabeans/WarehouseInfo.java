package javabeans;

public class WarehouseInfo {
	private int wh_no;
	private String wh_name;
	private String wh_adrs;
	private int wh_contain;
	private String admins;
	public int getWh_no() {
		return wh_no;
	}
	public void setWh_no(int wh_no) {
		this.wh_no = wh_no;
	}
	public String getWh_name() {
		return wh_name;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}
	public String getWh_adrs() {
		return wh_adrs;
	}
	public void setWh_adrs(String wh_adrs) {
		this.wh_adrs = wh_adrs;
	}
	public int getWh_contain() {
		return wh_contain;
	}
	public void setWh_contain(int wh_contain) {
		this.wh_contain = wh_contain;
	}
	public String getAdmins() {
		return admins;
	}
	public void setAdmins(String admins) {
		this.admins = admins;
	}
}
