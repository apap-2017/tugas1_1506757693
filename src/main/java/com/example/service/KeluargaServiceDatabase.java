package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;

	@Override
	public KeluargaModel selectInfoKeluarga(String nomor_kk) {
		log.info("memilih keluarga dengan NKK ", nomor_kk);
		return keluargaMapper.selectInfoKeluarga(nomor_kk);
	}

	@Override
	public KeluargaModel selectKeluarga(String id) {
		log.info("select anggota keluarga");
		return keluargaMapper.selectKeluarga(id);
	}

	@Override
	public void tambahKeluarga(KeluargaModel keluarga) {
		log.info("menambah keluarga");
		keluargaMapper.tambahKeluarga(keluarga);
	}
}
