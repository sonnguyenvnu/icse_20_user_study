/*Copyright Â©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**base model for reduce model codes
 * @author Lemon
 * @use extends BaseModel
 */
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;       //ä¸»é”®ï¼Œå”¯ä¸€æ ‡è¯†
	private Long userId;   //å¯¹åº”Userè¡¨ä¸­çš„idï¼Œå¤–é”®
	private Long date;     //åˆ›å»ºæ—¶é—´ï¼ŒJSONæ²¡æœ‰Date,TimeStampç±»åž‹ï¼Œéƒ½ä¼šè¢«è½¬æˆ?Longï¼Œä¸?èƒ½ç”¨ï¼?

	public Long getId() {
		return id;
	}
	public BaseModel setId(Long id) {
		this.id = id;
		return this;
	}
	public Long getUserId() {
		return userId;
	}
	public BaseModel setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Long getDate() {
		return date;
	}
	public BaseModel setDate(Long date) {
		this.date = date;
		return this;
	}
	
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	/**èŽ·å?–å½“å‰?æ—¶é—´æˆ³
	 * @return
	 */
	public static Timestamp currentTimeStamp() {  
	    return new Timestamp(new Date().getTime());  
	}
	/**èŽ·å?–æ—¶é—´æˆ³ TODO åˆ¤ç©ºï¼Ÿ è¿˜æ˜¯è¦?æŠ¥é”™ï¼Ÿ
	 * @param time
	 * @return
	 */
	public static Timestamp getTimeStamp(String time) {
		return Timestamp.valueOf(time);
	}
	/**èŽ·å?–æ—¶é—´æ¯«ç§’å€¼ TODO åˆ¤ç©ºï¼Ÿ è¿˜æ˜¯è¦?æŠ¥é”™ï¼Ÿ
	 * @param time
	 * @return
	 */
	public static long getTimeMillis(String time) {
		return getTimeStamp(time).getTime();
	}
	
	
	//åˆ¤æ–­æ˜¯å?¦ä¸ºç©º <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**åˆ¤æ–­arrayæ˜¯å?¦ä¸ºç©º
	 * @param array
	 * @return
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length <= 0;
	}
	/**åˆ¤æ–­collectionæ˜¯å?¦ä¸ºç©º
	 * @param collection
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}
	/**åˆ¤æ–­mapæ˜¯å?¦ä¸ºç©º
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}
	//åˆ¤æ–­æ˜¯å?¦ä¸ºç©º >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	//åˆ¤æ–­æ˜¯å?¦åŒ…å?« <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**åˆ¤æ–­arrayæ˜¯å?¦åŒ…å?«a
	 * @param array
	 * @param a
	 * @return
	 */
	public static <T> boolean isContain(T[] array, T a) {
		return array == null ? false : Arrays.asList(array).contains(a);
	}
	/**åˆ¤æ–­collectionæ˜¯å?¦åŒ…å?«object
	 * @param collection
	 * @param object
	 * @return
	 */
	public static <T> boolean isContain(Collection<T> collection, T object) {
		return collection != null && collection.contains(object);
	}
	/**åˆ¤æ–­mapæ˜¯å?¦åŒ…å?«key
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param key
	 * @return
	 */
	public static <K, V> boolean isContainKey(Map<K, V> map, K key) {
		return map != null && map.containsKey(key);
	}
	/**åˆ¤æ–­mapæ˜¯å?¦åŒ…å?«value
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param value
	 * @return
	 */
	public static <K, V> boolean isContainValue(Map<K, V> map, V value) {
		return map != null && map.containsValue(value);
	}
	//åˆ¤æ–­æ˜¯å?¦ä¸ºåŒ…å?« >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	//èŽ·å?–é›†å?ˆé•¿åº¦ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–æ•°é‡?
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> int count(T[] array) {
		return array == null ? 0 : array.length;
	}
	/**èŽ·å?–æ•°é‡?
	 * @param <T>
	 * @param collection List, Vector, Setç­‰éƒ½æ˜¯Collectionçš„å­?ç±»
	 * @return
	 */
	public static <T> int count(Collection<T> collection) {
		return collection == null ? 0 : collection.size();
	}
	/**èŽ·å?–æ•°é‡?
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> int count(Map<K, V> map) {
		return map == null ? 0 : map.size();
	}
	//èŽ·å?–é›†å?ˆé•¿åº¦ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	//èŽ·å?–é›†å?ˆé•¿åº¦ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> T get(T[] array, int position) {
		return position < 0 || position >= count(array) ? null : array[position];
	}
	/**èŽ·å?–
	 * @param <T>
	 * @param collection List, Vector, Setç­‰éƒ½æ˜¯Collectionçš„å­?ç±»
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Collection<T> collection, int position) {
		return collection == null ? null : (T) get(collection.toArray(), position);
	}
	/**èŽ·å?–
	 * @param <K>
	 * @param <V>
	 * @param map null ? null
	 * @param key null ? null : map.get(key);
	 * @return
	 */
	public static <K, V> V get(Map<K, V> map, K key) {
		return key == null || map == null ? null : map.get(key);
	}
	//èŽ·å?–é›†å?ˆé•¿åº¦ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	//èŽ·å?–é?žåŸºæœ¬ç±»åž‹å¯¹åº”åŸºæœ¬ç±»åž‹çš„é?žç©ºå€¼ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–é?žç©ºå€¼
	 * @param value
	 * @return
	 */
	public static boolean value(Boolean value) {
		return value == null ? false : value;
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param value
	 * @return
	 */
	public static int value(Integer value) {
		return value == null ? 0 : value;
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param value
	 * @return
	 */
	public static long value(Long value) {
		return value == null ? 0 : value;
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param value
	 * @return
	 */
	public static float value(Float value) {
		return value == null ? 0 : value;
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param value
	 * @return
	 */
	public static double value(Double value) {
		return value == null ? 0 : value;
	}
	//èŽ·å?–é?žåŸºæœ¬ç±»åž‹å¯¹åº”åŸºæœ¬ç±»åž‹çš„é?žç©ºå€¼ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**indexæ˜¯å?¦åœ¨arré•¿åº¦èŒƒå›´å†…
	 * @param index
	 * @param array
	 * @return
	 */
	public static boolean isIndexInRange(Integer index, Object[] array) {
		return index != null && index >= 0 && index < count(array);
	}

	/**èŽ·å?–åœ¨arré•¿åº¦èŒƒå›´å†…çš„index
	 * defaultIndex = 0
	 * @param index
	 * @param array
	 * @return
	 */
	public static int getIndexInRange(Integer index, Object[] array) {
		return getIndexInRange(index, array, 0);
	}
	/**èŽ·å?–åœ¨arré•¿åº¦èŒƒå›´å†…çš„index
	 * @param index
	 * @param array
	 * @param defaultIndex
	 * @return
	 */
	public static int getIndexInRange(Integer index, Object[] array, int defaultIndex) {
		return isIndexInRange(index, array) ? index : defaultIndex;
	}

	/**èŽ·å?–åœ¨arré•¿åº¦èŒƒå›´å†…çš„index
	 * defaultIndex = 0
	 * @param <T>
	 * @param index
	 * @param array
	 * @return
	 */
	public static <T> T getInRange(Integer index, T[] array) {
		return getInRange(index, array, 0);
	}
	/**èŽ·å?–åœ¨arré•¿åº¦èŒƒå›´å†…çš„index
	 * @param <T>
	 * @param index
	 * @param array
	 * @param defaultIndex
	 * @return
	 */
	public static <T> T getInRange(Integer index, T[] array, int defaultIndex) {
		return get(array, getIndexInRange(index, array, defaultIndex));
	}

}
