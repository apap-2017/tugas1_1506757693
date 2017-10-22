package com.example.service;

import com.example.model.KeluargaModel;

public interface KeluargaService {
	KeluargaModel selectInfoKeluarga(String nomor_kk);

	KeluargaModel selectKeluarga(String id);

	void tambahKeluarga(KeluargaModel keluarga);
}
