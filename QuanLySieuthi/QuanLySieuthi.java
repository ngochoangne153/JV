import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySieuthi {
    private ArrayList<SanPham> danhSachSanPham;
    private ArrayList<HoaDon> danhSachHoaDon;
    private ArrayList<NhanVien> danhSachNhanVien;
    private Scanner scanner;
    private NhanVien nhanVienDangNhap;
    
    private static final String FILE_SAN_PHAM = "sanpham.txt";
    private static final String FILE_HOA_DON = "hoadon.txt";
    private static final String FILE_NHAN_VIEN = "nhanvien.txt";

    public QuanLySieuthi() {
        danhSachSanPham = new ArrayList<>();
        danhSachHoaDon = new ArrayList<>();
        danhSachNhanVien = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        // Doc du lieu tu file
        docDuLieuSanPham();
        docDuLieuHoaDon();
        docDuLieuNhanVien();
        
        // Neu khong co du lieu, khoi tao mau
        if (danhSachSanPham.isEmpty()) {
            khoiTaoDuLieuSanPhamMau();
        }
        if (danhSachNhanVien.isEmpty()) {
            khoiTaoDuLieuNhanVienMau();
        }
    }

    private void khoiTaoDuLieuSanPhamMau() {
        themSanPham(new SanPham("SP001", "Gao", 25000, 100));
        themSanPham(new SanPham("SP002", "Duong", 15000, 50));
        themSanPham(new SanPham("SP003", "Sua", 10000, 80));
        themSanPham(new SanPham("SP004", "Mi goi", 5000, 200));
        themSanPham(new SanPham("SP005", "Nuoc ngot", 12000, 60));
        luuDuLieuSanPham();
    }
    
    private void khoiTaoDuLieuNhanVienMau() {
        // Tai khoan quan ly
        themNhanVien(new NhanVien("NV001", "Nguyen Van Quan Ly", "admin", "admin", "QuanLy"));
        // Tai khoan nhan vien
        themNhanVien(new NhanVien("NV002", "Tran Thi Nhan Vien", "nhanvien", "123456", "NhanVien"));
        luuDuLieuNhanVien();
    }

    // ========== FILE I/O METHODS ==========
    
    private void docDuLieuNhanVien() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NHAN_VIEN))) {
            String line;
            while ((line = reader.readLine()) != null) {
                NhanVien nv = NhanVien.fromFileString(line);
                if (nv != null) {
                    danhSachNhanVien.add(nv);
                }
            }
            System.out.println("Da doc " + danhSachNhanVien.size() + " nhan vien tu file");
        } catch (FileNotFoundException e) {
            System.out.println("Chua co file nhan vien. Tao moi...");
        } catch (IOException e) {
            System.out.println("Loi khi doc file nhan vien: " + e.getMessage());
        }
    }
    
    private void luuDuLieuNhanVien() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NHAN_VIEN))) {
            for (NhanVien nv : danhSachNhanVien) {
                writer.println(nv.toFileString());
            }
            System.out.println("Da luu " + danhSachNhanVien.size() + " nhan vien vao file");
        } catch (IOException e) {
            System.out.println("Loi khi luu file nhan vien: " + e.getMessage());
        }
    }
    
    private void docDuLieuSanPham() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_SAN_PHAM))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SanPham sp = SanPham.fromFileString(line);
                if (sp != null) {
                    danhSachSanPham.add(sp);
                }
            }
            System.out.println("Da doc " + danhSachSanPham.size() + " san pham tu file");
        } catch (FileNotFoundException e) {
            System.out.println("Chua co file san pham. Tao moi...");
        } catch (IOException e) {
            System.out.println("Loi khi doc file san pham: " + e.getMessage());
        }
    }
    
    private void luuDuLieuSanPham() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_SAN_PHAM))) {
            for (SanPham sp : danhSachSanPham) {
                writer.println(sp.toFileString());
            }
            System.out.println("Da luu " + danhSachSanPham.size() + " san pham vao file");
        } catch (IOException e) {
            System.out.println("Loi khi luu file san pham: " + e.getMessage());
        }
    }
    
    private void docDuLieuHoaDon() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_HOA_DON))) {
            String line;
            while ((line = reader.readLine()) != null) {
                HoaDon hd = HoaDon.fromFileString(line);
                if (hd != null) {
                    danhSachHoaDon.add(hd);
                }
            }
            System.out.println("Da doc " + danhSachHoaDon.size() + " hoa don tu file");
        } catch (FileNotFoundException e) {
            System.out.println("Chua co file hoa don. Tao moi...");
        } catch (IOException e) {
            System.out.println("Loi khi doc file hoa don: " + e.getMessage());
        }
    }
    
    private void luuDuLieuHoaDon() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_HOA_DON))) {
            for (HoaDon hd : danhSachHoaDon) {
                writer.println(hd.toFileString());
            }
            System.out.println("Da luu " + danhSachHoaDon.size() + " hoa don vao file");
        } catch (IOException e) {
            System.out.println("Loi khi luu file hoa don: " + e.getMessage());
        }
    }
    
    private void luuTatCaDuLieu() {
        luuDuLieuSanPham();
        luuDuLieuHoaDon();
        luuDuLieuNhanVien();
        System.out.println("Da luu toan bo du lieu thanh cong!");
    }

    // ========== NHAN VIEN METHODS ==========
    
    public void themNhanVien(NhanVien nhanVien) {
        danhSachNhanVien.add(nhanVien);
        luuDuLieuNhanVien();
    }
    
    public void hienThiDanhSachNhanVien() {
        System.out.println("\n===== DANH SACH NHAN VIEN =====");
        System.out.println("---------------------------------------------------");
        for (NhanVien nv : danhSachNhanVien) {
            nv.hienThiThongTin();
        }
        System.out.println("===================================================\n");
    }
    
    public void themNhanVienMoi() {
        System.out.println("\n===== THEM NHAN VIEN MOI =====");
        
        System.out.print("Nhap ma nhan vien: ");
        String maNV = scanner.nextLine();
        
        System.out.print("Nhap ten nhan vien: ");
        String tenNV = scanner.nextLine();
        
        System.out.print("Nhap ten dang nhap: ");
        String tenDN = scanner.nextLine();
        
        System.out.print("Nhap mat khau: ");
        String matKhau = scanner.nextLine();
        
        System.out.print("Nhap chuc vu (QuanLy/NhanVien): ");
        String chucVu = scanner.nextLine();
        
        themNhanVien(new NhanVien(maNV, tenNV, tenDN, matKhau, chucVu));
        System.out.println("Da them nhan vien moi thanh cong!");
    }
    
    public void capNhatNhanVien() {
        System.out.println("\n===== CAP NHAT NHAN VIEN =====");
        hienThiDanhSachNhanVien();
        
        System.out.print("Nhap ma nhan vien can cap nhat: ");
        String maNV = scanner.nextLine();
        
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }
        
        System.out.println("Thong tin hien tai:");
        nv.hienThiThongTin();
        
        System.out.println("\nChon muc can cap nhat:");
        System.out.println("1. Cap nhat ten");
        System.out.println("2. Cap nhat mat khau");
        System.out.println("3. Cap nhat chuc vu");
        System.out.print("Lua chon cua ban: ");
        
        int luaChon = scanner.nextInt();
        scanner.nextLine();
        
        switch (luaChon) {
            case 1:
                System.out.print("Nhap ten moi: ");
                String tenMoi = scanner.nextLine();
                nv.setTenNhanVien(tenMoi);
                break;
            case 2:
                System.out.print("Nhap mat khau moi: ");
                String mkMoi = scanner.nextLine();
                nv.setMatKhau(mkMoi);
                break;
            case 3:
                System.out.print("Nhap chuc vu moi (QuanLy/NhanVien): ");
                String cvMoi = scanner.nextLine();
                nv.setChucVu(cvMoi);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }
        
        luuDuLieuNhanVien();
        System.out.println("Cap nhat thanh cong!");
        nv.hienThiThongTin();
    }
    
    public void xoaNhanVien() {
        System.out.println("\n===== XOA NHAN VIEN =====");
        hienThiDanhSachNhanVien();
        
        System.out.print("Nhap ma nhan vien can xoa: ");
        String maNV = scanner.nextLine();
        
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }
        
        System.out.print("Ban co chac chan muon xoa '" + nv.getTenNhanVien() + "'? (y/n): ");
        String xacNhan = scanner.nextLine();
        
        if (xacNhan.equalsIgnoreCase("y")) {
            danhSachNhanVien.remove(nv);
            luuDuLieuNhanVien();
            System.out.println("Da xoa nhan vien thanh cong!");
        } else {
            System.out.println("Huy xoa nhan vien.");
        }
    }
    
    private NhanVien timNhanVienTheoMa(String maNV) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMaNhanVien().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }
    
    private NhanVien dangNhap() {
        System.out.println("\n===== DANG NHAP HE THONG =====");
        System.out.print("Ten dang nhap: ");
        String tenDN = scanner.nextLine();
        
        System.out.print("Mat khau: ");
        String matKhau = scanner.nextLine();
        
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getTenDangNhap().equals(tenDN) && nv.getMatKhau().equals(matKhau)) {
                System.out.println("Dang nhap thanh cong! Chao mung " + nv.getTenNhanVien());
                return nv;
            }
        }
        
        System.out.println("Ten dang nhap hoac mat khau khong dung!");
        return null;
    }

    // ========== BUSINESS METHODS ==========
    
    public void themSanPham(SanPham sanPham) {
        danhSachSanPham.add(sanPham);
        luuDuLieuSanPham();
    }

    public void hienThiDanhSachSanPham() {
        System.out.println("\n===== DANH SACH SAN PHAM =====");
        System.out.println("---------------------------------------------------");
        for (SanPham sp : danhSachSanPham) {
            sp.hienThiThongTin();
        }
        System.out.println("===================================================\n");
    }

    public void taoHoaDonMoi() {
        if (nhanVienDangNhap == null) {
            System.out.println("Vui long dang nhap truoc khi tao hoa don!");
            return;
        }
        
        System.out.println("\n===== TAO HOA DON MOI =====");
        System.out.println("Nhan vien: " + nhanVienDangNhap.getTenNhanVien());
        System.out.print("Nhap ngay tao hoa don (vd: 20/12/2024): ");
        String ngayTao = scanner.nextLine();
        
        HoaDon hoaDonMoi = new HoaDon(ngayTao, nhanVienDangNhap.getTenNhanVien());
        boolean tiepTucMua = true;
        
        while (tiepTucMua) {
            System.out.println("\n--- Danh sach san pham ---");
            hienThiDanhSachSanPham();
            
            System.out.print("Nhap ma san pham muon mua (hoac 'x' de ket thuc): ");
            String maSP = scanner.nextLine();
            
            if (maSP.equalsIgnoreCase("x")) {
                break;
            }
            
            SanPham sanPhamTimThay = timSanPhamTheoMa(maSP);
            if (sanPhamTimThay == null) {
                System.out.println("Khong tim thay san pham voi ma: " + maSP);
                continue;
            }
            
            System.out.print("Nhap so luong muon mua: ");
            int soLuong = scanner.nextInt();
            scanner.nextLine();
            
            hoaDonMoi.themSanPham(sanPhamTimThay, soLuong);
            
            System.out.print("Tiep tuc mua hang? (y/n): ");
            String luaChon = scanner.nextLine();
            tiepTucMua = luaChon.equalsIgnoreCase("y");
        }
        
        if (hoaDonMoi.getTongTien() > 0) {
            danhSachHoaDon.add(hoaDonMoi);
            luuDuLieuSanPham();
            luuDuLieuHoaDon();
            System.out.println("\n=== HOA DON DA TAO ===");
            hoaDonMoi.hienThiHoaDon();
        } else {
            System.out.println("Hoa don trong! Khong luu hoa don.");
        }
    }

    private SanPham timSanPhamTheoMa(String maSP) {
        for (SanPham sp : danhSachSanPham) {
            if (sp.getMaSanPham().equalsIgnoreCase(maSP)) {
                return sp;
            }
        }
        return null;
    }

    public void hienThiTatCaHoaDon() {
        System.out.println("\n===== DANH SACH HOA DON =====");
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Chua co hoa don nao!");
        } else {
            for (HoaDon hd : danhSachHoaDon) {
                hd.hienThiHoaDon();
            }
            
            double tongDoanhThu = tinhTongDoanhThu();
            System.out.printf("=== TONG DOANH THU: %.0f VND ===\n", tongDoanhThu);
        }
    }

    private double tinhTongDoanhThu() {
        double tong = 0;
        for (HoaDon hd : danhSachHoaDon) {
            tong += hd.getTongTien();
        }
        return tong;
    }

    public void themSanPhamMoi() {
        System.out.println("\n===== THEM SAN PHAM MOI =====");
        
        System.out.print("Nhap ma san pham: ");
        String maSP = scanner.nextLine();
        
        System.out.print("Nhap ten san pham: ");
        String tenSP = scanner.nextLine();
        
        System.out.print("Nhap gia san pham: ");
        double giaSP = scanner.nextDouble();
        
        System.out.print("Nhap so luong: ");
        int soLuong = scanner.nextInt();
        scanner.nextLine();
        
        themSanPham(new SanPham(maSP, tenSP, giaSP, soLuong));
        System.out.println("Da them san pham moi thanh cong!");
    }

    public void capNhatSanPham() {
        System.out.println("\n===== CAP NHAT SAN PHAM =====");
        hienThiDanhSachSanPham();
        
        System.out.print("Nhap ma san pham can cap nhat: ");
        String maSP = scanner.nextLine();
        
        SanPham sanPham = timSanPhamTheoMa(maSP);
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham!");
            return;
        }
        
        System.out.println("Thong tin hien tai:");
        sanPham.hienThiThongTin();
        
        System.out.println("\nChon muc can cap nhat:");
        System.out.println("1. Cap nhat gia");
        System.out.println("2. Cap nhat so luong");
        System.out.println("3. Cap nhat ca gia va so luong");
        System.out.print("Lua chon cua ban: ");
        
        int luaChon = scanner.nextInt();
        scanner.nextLine();
        
        switch (luaChon) {
            case 1:
                System.out.print("Nhap gia moi: ");
                double giaMoi = scanner.nextDouble();
                scanner.nextLine();
                sanPham.setGiaSanPham(giaMoi);
                break;
            case 2:
                System.out.print("Nhap so luong moi: ");
                int soLuongMoi = scanner.nextInt();
                scanner.nextLine();
                sanPham.setSoLuong(soLuongMoi);
                break;
            case 3:
                System.out.print("Nhap gia moi: ");
                double gia = scanner.nextDouble();
                System.out.print("Nhap so luong moi: ");
                int soLuong = scanner.nextInt();
                scanner.nextLine();
                sanPham.setGiaSanPham(gia);
                sanPham.setSoLuong(soLuong);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }
        
        luuDuLieuSanPham();
        System.out.println("Cap nhat thanh cong!");
        sanPham.hienThiThongTin();
    }
    
    public void xoaSanPham() {
        System.out.println("\n===== XOA SAN PHAM =====");
        hienThiDanhSachSanPham();
        
        System.out.print("Nhap ma san pham can xoa: ");
        String maSP = scanner.nextLine();
        
        SanPham sanPham = timSanPhamTheoMa(maSP);
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham!");
            return;
        }
        
        System.out.print("Ban co chac chan muon xoa '" + sanPham.getTenSanPham() + "'? (y/n): ");
        String xacNhan = scanner.nextLine();
        
        if (xacNhan.equalsIgnoreCase("y")) {
            danhSachSanPham.remove(sanPham);
            luuDuLieuSanPham();
            System.out.println("Da xoa san pham thanh cong!");
        } else {
            System.out.println("Huy xoa san pham.");
        }
    }
    
    public void dangXuat() {
        nhanVienDangNhap = null;
        System.out.println("Da dang xuat thanh cong!");
    }

    public void chayUngDung() {
        int luaChon;
        
        // Dang nhap
        nhanVienDangNhap = dangNhap();
        if (nhanVienDangNhap == null) {
            System.out.println("Dang nhap that bai. Thoat chuong trinh.");
            return;
        }
        
        do {
            hienThiMenu();
            System.out.print("Nhap lua chon cua ban: ");
            luaChon = scanner.nextInt();
            scanner.nextLine();
            
            xuLyLuaChon(luaChon);
            
        } while (luaChon != 0);
        
        luuTatCaDuLieu();
        System.out.println("Cam on ban da su dung phan mem!");
        scanner.close();
    }

    private void hienThiMenu() {
        System.out.println("\n===== HE THONG QUAN LY SIEU THI MINI =====");
        System.out.println("Nhan vien: " + (nhanVienDangNhap != null ? nhanVienDangNhap.getTenNhanVien() : "Chua dang nhap"));
        System.out.println("Chuc vu: " + (nhanVienDangNhap != null ? nhanVienDangNhap.getChucVu() : ""));
        System.out.println("==========================================");
        
        System.out.println("1. Hien thi danh sach san pham");
        System.out.println("2. Tao hoa don moi");
        System.out.println("3. Hien thi tat ca hoa don");
        
        // Chi hien thi menu quan ly neu la quan ly
        if (nhanVienDangNhap != null && nhanVienDangNhap.getChucVu().equals("QuanLy")) {
            System.out.println("4. Them san pham moi");
            System.out.println("5. Cap nhat san pham");
            System.out.println("6. Xoa san pham");
            System.out.println("7. Quan ly nhan vien");
            System.out.println("8. Luu du lieu (backup)");
            System.out.println("9. Dang xuat");
            System.out.println("0. Thoat");
        } else {
            System.out.println("9. Dang xuat");
            System.out.println("0. Thoat");
        }
        System.out.println("==========================================");
    }
    
    private void hienThiMenuQuanLyNhanVien() {
        System.out.println("\n===== QUAN LY NHAN VIEN =====");
        System.out.println("1. Hien thi danh sach nhan vien");
        System.out.println("2. Them nhan vien moi");
        System.out.println("3. Cap nhat nhan vien");
        System.out.println("4. Xoa nhan vien");
        System.out.println("0. Quay lai");
        System.out.println("=============================");
    }

    private void xuLyLuaChon(int luaChon) {
        boolean isQuanLy = nhanVienDangNhap != null && nhanVienDangNhap.getChucVu().equals("QuanLy");
        
        switch (luaChon) {
            case 1:
                hienThiDanhSachSanPham();
                break;
            case 2:
                taoHoaDonMoi();
                break;
            case 3:
                hienThiTatCaHoaDon();
                break;
            case 4:
                if (isQuanLy) themSanPhamMoi();
                else System.out.println("Khong co quyen truy cap!");
                break;
            case 5:
                if (isQuanLy) capNhatSanPham();
                else System.out.println("Khong co quyen truy cap!");
                break;
            case 6:
                if (isQuanLy) xoaSanPham();
                else System.out.println("Khong co quyen truy cap!");
                break;
            case 7:
                if (isQuanLy) quanLyNhanVien();
                else System.out.println("Khong co quyen truy cap!");
                break;
            case 8:
                if (isQuanLy) luuTatCaDuLieu();
                else System.out.println("Khong co quyen truy cap!");
                break;
            case 9:
                dangXuat();
                nhanVienDangNhap = dangNhap();
                if (nhanVienDangNhap == null) {
                    System.out.println("Dang nhap that bai. Thoat chuong trinh.");
                    luaChon = 0;
                }
                break;
            case 0:
                System.out.println("Dang thoat...");
                break;
            default:
                System.out.println("Lua chon khong hop le! Vui long chon lai.");
        }
        
        if (luaChon != 0) {
            System.out.print("\nNhan Enter de tiep tuc...");
            scanner.nextLine();
        }
    }
    
    private void quanLyNhanVien() {
        int luaChon;
        do {
            hienThiMenuQuanLyNhanVien();
            System.out.print("Nhap lua chon cua ban: ");
            luaChon = scanner.nextInt();
            scanner.nextLine();
            
            switch (luaChon) {
                case 1:
                    hienThiDanhSachNhanVien();
                    break;
                case 2:
                    themNhanVienMoi();
                    break;
                case 3:
                    capNhatNhanVien();
                    break;
                case 4:
                    xoaNhanVien();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
            
            if (luaChon != 0) {
                System.out.print("\nNhan Enter de tiep tuc...");
                scanner.nextLine();
            }
        } while (luaChon != 0);
    }
}