package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;
import com.example.service.KeluargaService;
import com.example.service.PendudukService;

@Controller
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;

	@Autowired
	KeluargaService keluargaDAO;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/penduduk")
	public String view(Model model, @RequestParam(value = "nik", required = false) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			penduduk.getStatusKematian();
			penduduk.setTanggalLahir(penduduk.getTanggal_lahir());
			model.addAttribute("penduduk", penduduk);
			return "viewpenduduk";
		} else {
			model.addAttribute("nik", nik);
			return "penduduk-not-found";
		}
	}

	@RequestMapping("/penduduk/tambah")
	public String tambah() {
		return "form-tambah-penduduk";
	}

	@RequestMapping(value = "/penduduk/tambah/submit", method = RequestMethod.POST)
	public String tambahPendudukSubmit(Model model, @ModelAttribute PendudukModel penduduk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());

		String tanggalLahir = "";
		if (penduduk.getJenis_kelamin() == 0) {
			String[] temp = penduduk.getTanggal_lahir().split("-");
			tanggalLahir = temp[2] + temp[1] + temp[0].substring(2);
		} else {
			String[] temp = penduduk.getTanggal_lahir().split("-");
			int tanggal = Integer.parseInt(temp[2]) + 40;
			tanggalLahir = tanggal + temp[1] + temp[0].substring(2);
		}

		String nikTemp = keluarga.getNomor_kk().substring(0, 6) + tanggalLahir + "0001";
		long nikLong = Long.parseLong(nikTemp);

		while (pendudukDAO.selectPenduduk(nikTemp) != null) {
			nikTemp = String.valueOf(nikLong++);
		}
		penduduk.setNik(nikTemp);
		pendudukDAO.tambahPenduduk(penduduk);

		model.addAttribute("nik", penduduk.getNik());
		return "tambah-penduduk-sukses";
	}

	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String ubah(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		model.addAttribute("penduduk", penduduk);
		return "form-ubah-penduduk";
	}

	@RequestMapping(value = "/penduduk/ubah/submit", method = RequestMethod.POST)
	public String ubahPendudukSubmit(Model model, @ModelAttribute PendudukModel penduduk) {
		String nikLama = penduduk.getNik();

		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());

		String tanggalLahir = "";
		if (penduduk.getJenis_kelamin() == 0) {
			String[] temp = penduduk.getTanggal_lahir().split("-");
			tanggalLahir = temp[2] + temp[1] + temp[0].substring(2);
		} else {
			String[] temp = penduduk.getTanggal_lahir().split("-");
			int tanggal = Integer.parseInt(temp[2]) + 40;
			tanggalLahir = tanggal + temp[1] + temp[0].substring(2);
		}

		if (keluarga.getNomor_kk().substring(0, 6) != nikLama.substring(0, 6)
				&& tanggalLahir != nikLama.substring(6, 12)) {
			String nikTemp = keluarga.getNomor_kk().substring(0, 6) + tanggalLahir + "0001";
			long nikLong = Long.parseLong(nikTemp);

			while (pendudukDAO.selectPenduduk(nikTemp) != null) {
				nikTemp = String.valueOf(nikLong++);
			}
			penduduk.setNik(nikTemp);
		}
		pendudukDAO.ubahPenduduk(penduduk);

		model.addAttribute("nik", nikLama);
		return "ubah-penduduk-sukses";
	}
	
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String updateStatusKematian(Model model, @RequestParam(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		penduduk.setIs_wafat(1);
		model.addAttribute("nik", nik);
		return "nik-nonaktif";
	}

}