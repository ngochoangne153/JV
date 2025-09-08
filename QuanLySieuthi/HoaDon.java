import java.io.Serializable;
import java.util.ArrayList;

public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int demHoaDon = 0;
    
    private int maHoaDon;
    private String ngayTao;
    private String nhanVienBanHang; // Ten nhan vien ban hang
    private ArrayList<SanPham> danhSachSanPham;
    private double tongTien;

    public HoaDon(String ngayTao, String nhanVienBanHang) {
        this.maHoaDon = ++demHoaDon;
        this.ngayTao = ngayTao;
        this.nhanVienBanHang = nhanVienBanHang;
        this.danhSachSanPham = new ArrayList<>();
        this.tongTien = 0;
    }

    public void themSanPham(SanPham sanPham, int soLuong) {
        if (sanPham.getSoLuong() >= soLuong) {
            SanPham spMua = new SanPham(sanPham.getMaSanPham(), sanPham.getTenSanPham(), 
                                       sanPham.getGiaSanPham(), soLuong);
            danhSachSanPham.add(spMua);
            tongTien += sanPham.getGiaSanPham() * soLuong;
            
            sanPham.setSoLuong(sanPham.getSoLuong() - soLuong);
            
            System.out.println("Da them " + soLuong + " " + sanPham.getTenSanPham() + " vao hoa don");
        } else {
            System.out.println("Khong du so luong trong kho!");
        }
    }

    public void hienThiHoaDon() {
        System.out.println("\n===== HOA DON #" + maHoaDon + " =====");
        System.out.println("Ngay: " + ngayTao);
        System.out.println("Nhan vien: " + nhanVienBanHang);
        System.out.println("----------------------------------");
        
        for (SanPham sp : danhSachSanPham) {
            double thanhTien = sp.getGiaSanPham() * sp.getSoLuong();
            System.out.printf("%-15s %-8.0f x %-3d = %-8.0f\n", 
                sp.getTenSanPham(), sp.getGiaSanPham(), sp.getSoLuong(), thanhTien);
        }
        
        System.out.println("----------------------------------");
        System.out.printf("TONG TIEN: %.0f VND\n", tongTien);
        System.out.println("==================================\n");
    }

    public double getTongTien() { return tongTien; }
    public int getMaHoaDon() { return maHoaDon; }
    public String getNgayTao() { return ngayTao; }
    public String getNhanVienBanHang() { return nhanVienBanHang; }
    
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(maHoaDon).append(",").append(ngayTao).append(",")
          .append(nhanVienBanHang).append(",").append(tongTien);
        for (SanPham sp : danhSachSanPham) {
            sb.append(";").append(sp.toFileString());
        }
        return sb.toString();
    }
    
    public static HoaDon fromFileString(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length >= 4) {
            HoaDon hd = new HoaDon(parts[1], parts[2]);
            hd.maHoaDon = Integer.parseInt(parts[0]);
            hd.tongTien = Double.parseDouble(parts[3]);
            
            if (parts.length > 4) {
                String[] items = parts[4].split(";");
                for (String item : items) {
                    if (!item.isEmpty()) {
                        SanPham sp = SanPham.fromFileString(item);
                        if (sp != null) {
                            hd.danhSachSanPham.add(sp);
                        }
                    }
                }
            }
            return hd;
        }
        return null;
    }
}