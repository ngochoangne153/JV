import java.io.Serializable;

public class SanPham implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maSanPham;
    private String tenSanPham;
    private double giaSanPham;
    private int soLuong;

    public SanPham(String maSanPham, String tenSanPham, double giaSanPham, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() { return maSanPham; }
    public String getTenSanPham() { return tenNhanVien; }
    public double getGiaSanPham() { return giaSanPham; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setGiaSanPham(double giaSanPham) { this.giaSanPham = giaSanPham; }

    public void hienThiThongTin() {
        System.out.println("Ma SP: " + maSanPham + " | Ten: " + tenSanPham + 
                          " | Gia: " + giaSanPham + " | So luong: " + soLuong);
    }
    
    public String toFileString() {
        return maSanPham + "," + tenSanPham + "," + giaSanPham + "," + soLuong;
    }
    
    public static SanPham fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new SanPham(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
        }
        return null;
    }
}