package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("SELECT nik, nama, tempat_lahir, tanggal_lahir, id_keluarga, keluarga.id, alamat, rt, rw, id_kelurahan, kelurahan.id, nama_kelurahan, id_kecamatan, kecamatan.id, nama_kecamatan, id_kota, kota.id, nama_kota, golongan_darah, agama, status_perkawinan, pekerjaan, is_wni, is_wafat FROM penduduk, keluarga, kelurahan, kecamatan, kota WHERE nik = #{nik} AND id_keluarga = keluarga.id AND id_kelurahan = kelurahan.id AND id_kecamatan = kecamatan.id AND id_kota = kota.id")
	PendudukModel selectPenduduk (@Param("nik") String nik);

	@Select("SELECT nama, nik, jenis_kelamin, tempat_lahir, tanggal_lahir, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, is_wni, keluarga.id, id_keluarga FROM penduduk, keluarga WHERE keluarga.id = id_keluarga AND keluarga.id = #{id}")
	List<PendudukModel> selectAnggotaKeluarga(String id);
	
	@Insert("INSERT into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, 0)")
	void tambahPenduduk (PendudukModel penduduk);

	@Update("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, golongan_darah = #{golongan_darah}, agama = #{agama}, status_perkawinan = #{status_perkawinan}, pekerjaan = #{pekerjaan}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga} WHERE id = #{id}")
	void ubahPenduduk(PendudukModel penduduk);

	@Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
	void pendudukMati(@Param("nik") String nik);
}
