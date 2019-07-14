/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo.client.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import apijson.demo.client.model.CommentItem;

/**评论工具类
 * @author Lemon
 */
public class CommentUtil {

	/**�?�层列表
	 * @param list
	 * @return
	 */
	public static List<CommentItem> toSingleLevelList(List<CommentItem> list) {
		if (list == null || list.isEmpty()) {
			return list;
		}

		//parent和child分类
		Map<Long, CommentItem> parentMap = new LinkedHashMap<>();//added
		long id;
		long toId;
		for (CommentItem item : list) {
			id = item == null ? 0 : item.getId();
			if (id <= 0) {
				continue;
			}
			parentMap.put(id, item);
		}

		CommentItem parent;
		for (final CommentItem item : new ArrayList<>(parentMap.values())) {
			parent = null;
			toId = item.getToId();
			if (toId > 0) {
				parent = parentMap.get(toId);
				if (parent == null) {
					parentMap.remove(item.getId());
					continue;
				}
			}
			if (parent != null) {
				item.setToUser(parent.getUser());
				parentMap.put(item.getId(), item);
			}
		}

		return new ArrayList<>(parentMap.values());
	}

	/**�?�层(父�?二级)列表
	 * @param list
	 * @return
	 */
	public static List<CommentItem> toDoubleLevelList(List<CommentItem> list) {
		if (list == null || list.isEmpty()) {
			return list;
		}

		//parent和child分类
		Map<Long, CommentItem> parentMap = new LinkedHashMap<>();//added
		Map<Long, CommentItem> allChildMap = new LinkedHashMap<>();
		long id;
		long toId;
		for (CommentItem item : list) {
			id = item == null ? 0 : item.getId();
			if (id <= 0) {
				continue;
			}
			item.setChildList(null);//�?��?�?�?添加child

			toId = item.getToId();
			if (toId <= 0) {//parent
				parentMap.put(id, item);
			} else {//child
				allChildMap.put(id, item);
			}
		}

		//child放到parent的childList中
		boolean isFirst;
		CommentItem parent;
		List<CommentItem> childList;
		for (final CommentItem child : allChildMap.values()) {
			toId = child.getToId();
			isFirst = true;
			while (parentMap.containsKey(toId) == false) {//根�?�父评论一步步找到一级父评论
				parent = toId <= 0 ? null : allChildMap.get(toId);
				if (parent == null) {
					break;
				}
				if (isFirst) {
					isFirst = false;
					child.setToUser(parent.getUser());
				}

				toId = parent.getToId();//父评论的父评论的id
			}

			parent = parentMap.get(toId);
			if (parent == null) {
				continue;
			}
			if (toId == child.getToId()) {
				child.setToUser(parent.getUser());
			}

			childList = parent.getChildList();
			if (childList == null) {
				childList = new ArrayList<>();
			}
			childList.add(child);

			parent.setChildList(childList);
			parentMap.put(toId, parent);
		}

		return new ArrayList<>(parentMap.values());
	}

}
