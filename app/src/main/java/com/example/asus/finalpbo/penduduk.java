package com.example.asus.finalpbo;

public class penduduk {
    private String no;
    private String golongan;
    private Double pakai;
    private Double total;

    public penduduk() {
    }

    public penduduk(String no, String golongan, Double pakai, Double total) {
        this.no = no;
        this.golongan = golongan;
        this.pakai = pakai;
        this.total = total;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public Double getPakai() {
        return pakai;
    }

    public void setPakai(Double pakai) {
        this.pakai = pakai;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal() {
        if (golongan.equals("Kelompok 1")){
                if(pakai>10){
                    this.total=pakai*1050;
                } else {
                    this.total=pakai*1000;
                }
        } else if (golongan.equals("Kelompok 2")){
            if(pakai>10){
                this.total=pakai*2000;
            } else {
                this.total=pakai*1070;
            }
        } else {
            this.total= Double.valueOf("Tidak bisa");
        }
    }
}
