/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.permission.biz;

import com.roncoo.pay.permission.service.PmsMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author System
 * 
 * @since 2013-11-12
 */
@Component("pmsMenuBiz")
public class PmsMenuBiz {

	private static final Log log = LogFactory.getLog(PmsMenuBiz.class);

	@Autowired
	private PmsMenuService pmsMenuService;

	/**
	 * èŽ·å?–ç”¨äºŽç¼–åˆ¶è?œå?•æ—¶çš„æ ‘.
	 */
	@SuppressWarnings("rawtypes")
	public String getTreeMenu(String actionUrl) {
		List treeData = pmsMenuService.getListByParent(null);
		StringBuffer strJson = new StringBuffer();
		recursionTreeMenu("0", strJson, treeData, actionUrl);
		return strJson.toString();
	}

	/**
	 * é€’å½’è¾“å‡ºæ ‘å½¢è?œå?•
	 * 
	 * @param pId
	 * @param buffer
	 */
	@SuppressWarnings("rawtypes")
	private void recursionTreeMenu(String pId, StringBuffer buffer, List list, String url) {
		if (pId.equals("0")) {
			buffer.append("<ul class=\"tree treeFolder collapse \" >");
		} else {
			buffer.append("<ul>");
		}
		List<Map> listMap = getSonMenuListByPid(pId, list);
		for (Map map : listMap) {
			String id = map.get("id").toString();// id
			String name = map.get("name").toString();// å??ç§°
			String isLeaf = map.get("isLeaf").toString();// æ˜¯å?¦å?¶å­?ç§‘ç›®
			buffer.append("<li><a onclick=\"onClickMenuNode(" + id + ")\"  href=\"" + url + "?id=" + id + "\" target=\"ajax\" rel=\"jbsxBox\"  value=" + id + ">" + name + "</a>");
			if (!isLeaf.equals("1")) {
				recursionTreeMenu(id, buffer, list, url);
			}
			buffer.append("</li>");
		}
		buffer.append("</ul>");
	}

	/**
	 * æ ¹æ?®(pId)èŽ·å?–(menuList)ä¸­çš„æ‰€æœ‰å­?è?œå?•é›†å?ˆ.
	 * 
	 * @param pId
	 *            çˆ¶è?œå?•ID.
	 * @param menuList
	 *            è?œå?•é›†å?ˆ.
	 * @return sonMenuList.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getSonMenuListByPid(String pId, List menuList) {
		List sonMenuList = new ArrayList<Object>();
		for (Object menu : menuList) {
			Map map = (Map) menu;
			if (map != null) {
				String parentId = map.get("pId").toString();// çˆ¶id
				if (parentId.equals(pId)) {
					sonMenuList.add(map);
				}
			}
		}
		return sonMenuList;
	}

}
