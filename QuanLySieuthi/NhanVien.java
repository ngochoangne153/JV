import java.io.Serializable;

public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maNhanVien;
    private String tenNhanVien;
    private String tenDangNhap;
    private String matKhau;
    private String chucVu; // "QuanLy" hoặc "NhanVien"

    public NhanVien(String maNhanVien, String tenNhanVien, String tenDangNhap, String matKhau, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public String getMaNhanVien() { return maNhanVien; }
    public String getTenNhanVien() { return tenNhanVien; }
    public String getTenDangNhap() { return tenDangNhap; }
    public String getMatKhau() { return matKhau; }
    public String getChucVu() { return chucVu; }

    public void setTenNhanVien(String tenNhanVien) { this.tenNhanVien = tenNhanVien; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public void hienThiThongTin() {
        System.out.println("Ma NV: " + maNhanVien + " | Ten: " + tenNhanVien + 
                          " | Chuc vu: " + chucVu + " | TK: " + tenDangNhap);
    }
    
    public String toFileString() {
        return maNhanVien + "," + tenNhanVien + "," + tenDangNhap + "," + matKhau + "," + chucVu;
    }
    
    public static NhanVien fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new NhanVien(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }
}