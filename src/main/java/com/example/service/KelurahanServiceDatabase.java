package com.example.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
	private KelurahanMapper kelurahanMapper;

	@Override
	public KelurahanModel selectKelurahan(String id_kelurahan) {
		log.info("mengambil kelurahan dengan id " + id_kelurahan);
		return kelurahanMapper.selectKelurahan(id_kelurahan);
	}

}
