import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChamCong implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maNhanVien;
    private String tenNhanVien;
    private String ngayChamCong;
    private String gioVao;
    private String gioRa;
    private String trangThai;

    public ChamCong(String maNhanVien, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayChamCong = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.gioVao = new SimpleDateFormat("HH:mm:ss").format(new Date());
        this.gioRa = "";
        this.trangThai = "DangLam";
    }

    public void tanCa() {
        this.gioRa = new SimpleDateFormat("HH:mm:ss").format(new Date());
        this.trangThai = "DaTanCa";
    }

    public String getMaNhanVien() { return maNhanVien; }
    public String getTenNhanVien() { return tenNhanVien; }
    public String getNgayChamCong() { return ngayChamCong; }
    public String getGioVao() { return gioVao; }
    public String getGioRa() { return gioRa; }
    public String getTrangThai() { return trangThai; }

    public void hienThiThongTin() {
        System.out.println("Ma NV: " + maNhanVien + " | Ten: " + tenNhanVien);
        System.out.println("Ngay: " + ngayChamCong + " | Gio vao: " + gioVao);
        System.out.println("Gio ra: " + (gioRa.isEmpty() ? "Chua tan ca" : gioRa));
        System.out.println("Trang thai: " + trangThai);
        System.out.println("----------------------------------");
    }
    
    public String toFileString() {
        return maNhanVien + "," + tenNhanVien + "," + ngayChamCong + "," + 
               gioVao + "," + gioRa + "," + trangThai;
    }
    
    public static ChamCong fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            ChamCong cc = new ChamCong(parts[0], parts[1]);
            cc.ngayChamCong = parts[2];
            cc.gioVao = parts[3];
            cc.gioRa = parts[4];
            cc.trangThai = parts[5];
            return cc;
        }
        return null;
    }
}