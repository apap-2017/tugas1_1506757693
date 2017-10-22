package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	private String id;
	private String kode_kelurahan;
	private String id_kecamatan;
	private String nama_kecamatan;
	private String kode_pos;
}
