package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	@Autowired
	private KelurahanMapper kelurahanMapper;
	
	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		log.info("mengambil semua kelurahan");
		return kelurahanMapper.selectAllKelurahan();
	}
}
