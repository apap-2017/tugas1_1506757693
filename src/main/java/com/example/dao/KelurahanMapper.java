package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("SELECT * FROM kelurahan WHERE id = #{id_kelurahan}")
	KelurahanModel selectKelurahan(@Param("id_kelurahan") String id_kelurahan);

	@Select("SELECT * FROM kelurahan")
	List<KelurahanModel> selectAllKelurahan();
}
