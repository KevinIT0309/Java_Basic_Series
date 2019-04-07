package truutuong1phan_abstract;

public class BaoVe extends NhanVien
{
	final int luong = 15000;//final la khai bao bien hang so,khong the thay doi trong qua trinh thuc thi chuong trinh
	private int gio;
	
	
	
	public void setgio(int gio)
	{
		this.gio = gio;
	}
	
	
	@Override
	public int TinhLuong()
	{	
		return this.gio*luong;
	}
	
	
	@Override
	public void xuat()//ghi de lai ham ben class cha
	{
		System.out.println("luong bao ve la: "+TinhLuong());
	}
	
	
	public BaoVe()
	{
		super();
	}

	public BaoVe(String manv, String hoten,int gio)
	{
		super(manv, hoten);
		this.gio = gio;
	}
	
	public String toString ()
	{
		return "mabv: "+this.getManv() + "\thoten: "+this.getHoten()+"\t";
	}
	
}
