package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT nomor_kk, alamat, rt, rw, id_kelurahan, kelurahan.id, nama_kelurahan, id_kecamatan, kecamatan.id, nama_kecamatan, id_kota, kota.id, nama_kota FROM keluarga, kelurahan, kecamatan, kota WHERE nomor_kk = #{nomor_kk} AND id_kelurahan = kelurahan.id AND id_kecamatan = kecamatan.id AND id_kota = kota.id")
	@Results(value = { 
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), 
			@Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"), 
			@Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kode_kecamatan", column = "kode_kecamatan"),
			@Result(property = "nama_kota", column = "nama_kota"),
			@Result(property = "anggotaKeluarga", column = "nomor_kk", 
			javaType = List.class, 
			many = @Many(select = "selectAnggotaKeluarga"))
	})
	KeluargaModel selectInfoKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Select("SELECT * FROM keluarga, kelurahan, kecamatan, kota WHERE keluarga.id = #{id} AND id_kelurahan = kelurahan.id AND id_kecamatan = kecamatan.id AND id_kota = kota.id")
	@Results(value = { @Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), @Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"), @Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kode_kecamatan", column = "kode_kecamatan"),
			@Result(property = "nama_kota", column = "nama_kota"),
			@Result(property = "anggotaKeluarga", column = "nomor_kk", 
			javaType = List.class, many = @Many(select = "selectAnggotaKeluarga"))
	})
	KeluargaModel selectKeluarga(@Param("id") String id);

	@Select("SELECT * FROM penduduk, keluarga WHERE penduduk.id_keluarga = keluarga.id AND nomor_kk = #{nomor_kk}")
	List<PendudukModel> selectAnggotaKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) values (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, 0)")
    void tambahKeluarga (KeluargaModel keluarga);
}