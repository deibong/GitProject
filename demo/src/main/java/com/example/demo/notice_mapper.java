package kr.co.lee;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface notice_mapper {
	int notice_insert(notice_DTO nd);
}
