package com.geekq.admin.service.impl;

import com.geekq.admin.entity.SystemDictionaryItem;
import com.geekq.admin.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数�?�字典工具类
 * @author Administrator
 *
 */
@Component
public class SystemDictionaryUtil {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	public List<SystemDictionaryItem> list(String sn) {
		return systemDictionaryService.queryBySn(sn);
	}

}
