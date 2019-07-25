package com.myimooc.excel.boot.service;

import java.util.List;

import com.myimooc.excel.boot.domain.model.ImportDataDetail;
import com.myimooc.excel.boot.repository.ImportDataDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myimooc.excel.boot.domain.model.ImportData;
import com.myimooc.excel.boot.repository.ImportDataRepository;

/**
 * 数�?�导入详情业务类
 * @author ZhangCheng on 2017-07-08
 *
 */
@Service
public class ImportDataDeatilService {
	
	@Autowired
	private ImportDataRepository importDataRepository;
	@Autowired
	private ImportDataDetailRepository importDataDetailRepository;
	
	public List<ImportData> list(){
		return importDataRepository.findAll();
	}
	
	public void save(List<ImportDataDetail> importDataDetailList){
		
		importDataDetailRepository.save(importDataDetailList);
		
	}
	
}
