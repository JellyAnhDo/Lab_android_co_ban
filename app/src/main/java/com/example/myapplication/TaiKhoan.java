package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

// Lớp TaiKhoan
public class TaiKhoan implements Serializable {
    private int id;
    private String hoTen;
    private String soDienThoai;
    private String ngaySinh;
    private String diaChi;
    private String userName;
    private String password;


    public TaiKhoan(String hoTen, String soDienThoai, String userName, String password, String ngaySinh, String diaChi) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.userName = userName;
        this.password = password;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public TaiKhoan() {
    }
    public int getId() {
        return id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }
    public String getNgaySinh() {return ngaySinh; }
    public String getDiaChi() {return diaChi; }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setId(int id) { this.id = id; }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}