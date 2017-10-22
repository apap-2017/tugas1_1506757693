package com.example.service;

import com.example.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);

	void tambahPenduduk(PendudukModel penduduk);

	void ubahPenduduk(PendudukModel penduduk);
	
	void pendudukMati(String nik);
}
