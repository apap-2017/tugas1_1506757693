package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {
	@Autowired
	KelurahanService kelurahanDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	PendudukService pendudukDAO;
	
	@RequestMapping("/keluarga")
	public String viewKeluarga(Model model,
			@RequestParam(value="nomor_kk", required = false) String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectInfoKeluarga(nomor_kk);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "viewkeluarga";
		} else {
			model.addAttribute("nomor_kk", nomor_kk);
			return "keluarga-not-found";
		}	
	}
	
	@RequestMapping("/keluarga/tambah")
	public String tambahKeluarga() {
		return "form-tambah-keluarga";
	}
	
	@RequestMapping(value = "/keluarga/tambah/submit", method = RequestMethod.POST)
	public String tambahPendudukSubmit (Model model, @ModelAttribute KeluargaModel keluarga) {
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
		String kode_kelurahan = kelurahan.getKode_kelurahan();
		
		LocalDate date = LocalDate.now();
    	String tanggal = DateTimeFormatter.ofPattern("ddMMyy").format(date);
    	
		String nkkTemp = kode_kelurahan.substring(0,6) + tanggal + "0001";
		long nkkLong = Long.parseLong(nkkTemp);
		
		while (keluargaDAO.selectInfoKeluarga(String.valueOf(nkkLong)) != null) {
			nkkTemp = String.valueOf(nkkLong++);
		}
		keluarga.setNomor_kk(nkkTemp);
		keluargaDAO.tambahKeluarga(keluarga);
		
		model.addAttribute("nomor_kk", keluarga.getNomor_kk());
		return "tambah-keluarga-sukses";
	}
}
