package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info("memilih penduduk dengan NIK {}", nik);
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public void tambahPenduduk(PendudukModel penduduk) {
		log.info("menambah penduduk");
		pendudukMapper.tambahPenduduk(penduduk);
	}

	@Override
	public void ubahPenduduk(PendudukModel penduduk) {
		log.info("mengubah data penduduk");
		pendudukMapper.ubahPenduduk(penduduk);
	}

	@Override
	public void pendudukMati(String nik) {
		log.info("menonaktifkan NIK");
		pendudukMapper.pendudukMati(nik);
	}
}
