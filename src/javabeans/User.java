package javabeans;

public class User {
	private String uname="999";//用户名
	public void setUname(String uname) {
		this.uname = uname;
	}
	private String upass="999";//密码
	private int userid=999;//管理员编号
	private int ugrade=999;//权限
	private int uwh;
	
	public int getUwh() {
		return uwh;
	}

	public void setUwh(int uwh) {
		this.uwh = uwh;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUgrade() {
		return ugrade;
	}

	public void setUgrade(int ugrade) {
		this.ugrade = ugrade;
	}
	public String getUname() {
		return uname;
	}

}
