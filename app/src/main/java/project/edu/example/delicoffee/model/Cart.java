package project.edu.example.delicoffee.model;

public class Cart {
    private String tensp;
    private int soluong;
    private int tongtien;
    public Cart() {
    }

    public Cart(String tensp, int soluong, int tongtien) {
        this.tensp = tensp;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", tongtien=" + tongtien +
                '}';
    }
}
