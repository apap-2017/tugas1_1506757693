package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
	private String id;
	private String nik;
	private String nama;
	private String tempat_lahir;
	private String tanggal_lahir;
	private String lahir;
	private int jenis_kelamin;
	private String jenis_kelamin_string;
	private int is_wni;
	private String kewarganegaraan;
	private String id_keluarga;
	private String agama;
	private String pekerjaan;
	private String status_perkawinan;
	private String status_dalam_keluarga;
	private String golongan_darah;
	private int is_wafat;
	private String status_kematian;
	private String alamat;
	private String rt;
	private String rw;
	private String nama_kelurahan;
	private String nama_kecamatan;
	private String nama_kota;
	
	public String getStatusKematian() {
		if (is_wafat == 0) {
			status_kematian = "Hidup";
		} else {
			status_kematian = "Wafat";
		}
		return status_kematian;
		
	}
	
	public void setTanggalLahir(String tanggal_lahir) {
		String[] temp = tanggal_lahir.split("-");
		if (temp[1].equals("01")) {
			temp[1] = "Januari";
		} else if(temp[1].equals("02")) {
			temp[1] = "Februari";
		} else if(temp[1].equals("03")) {
			temp[1] = "Maret";
		} else if(temp[1].equals("04")) {
			temp[1] = "April";
		} else if(temp[1].equals("05")) {
			temp[1] = "Mei";
		} else if(temp[1].equals("06")) {
			temp[1] = "Juni";
		} else if(temp[1].equals("07")) {
			temp[1] = "Juli";
		} else if(temp[1].equals("08")) {
			temp[1] = "Agustus";
		} else if(temp[1].equals("09")) {
			temp[1] = "September";
		} else if(temp[1].equals("10")) {
			temp[1] = "Oktober";
		} else if(temp[1].equals("11")) {
			temp[1] = "November";
		} else {
			temp[1] = "Desember";
		}
		lahir = temp[2] + " " + temp[1] + " " + temp[0];
	}
}
