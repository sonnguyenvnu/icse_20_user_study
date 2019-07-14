/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.apijson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**use this class instead of com.alibaba.fastjson.JSONObject
 * @author Lemon
 * @see #put
 * @see #puts
 * @see #putsAll
 */
public class JSONObject extends com.alibaba.fastjson.JSONObject {
	private static final long serialVersionUID = 1L;
	
	private static final String TAG = "JSONObject";

	
	/**ordered
	 */
	public JSONObject() {
		super(true);
	}
	/**transfer Object to JSONObject
	 * @param object
	 * @see {@link #JSONObject(Object)}
	 */
	public JSONObject(Object object) {
		this(toJSONString(object));
	}
	/**parse JSONObject with JSON String
	 * @param json
	 * @see {@link #JSONObject(String)}
	 */
	public JSONObject(String json) {
		this(parseObject(json));
	}
	/**transfer com.alibaba.fastjson.JSONObject to JSONObject
	 * @param object
	 * @see {@link #putsAll(Map<? extends String, ? extends Object>)}
	 */
	public JSONObject(com.alibaba.fastjson.JSONObject object) {
		this();
		putsAll(object);
	}




	//judge <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public static final String KEY_ARRAY = "[]";

	/**判断是�?�为Array的key
	 * @param key
	 * @return
	 */
	public static boolean isArrayKey(String key) {
		return key != null && key.endsWith(KEY_ARRAY);
	}
	/**判断是�?�为对应Table的key
	 * @param key
	 * @return
	 */
	public static boolean isTableKey(String key) {
		return StringUtil.isBigName(key);
	}
	//judge >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//JSONObject内关键�? key <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	
	public static String KEY_ID = "id";
	public static String KEY_ID_IN = KEY_ID + "{}";
	public static String KEY_USER_ID = "userId";
	public static String KEY_USER_ID_IN = KEY_USER_ID + "{}";

	/**set "id":id in Table layer
	 * @param id
	 * @return
	 */
	public JSONObject setId(Long id) {
		return puts(KEY_ID, id);
	}
	/**set "id{}":[] in Table layer
	 * @param list
	 * @return
	 */
	public JSONObject setIdIn(List<Object> list) {
		return puts(KEY_ID_IN, list);
	}

	/**set "userId":userId in Table layer
	 * @param id
	 * @return
	 */
	public JSONObject setUserId(Long id) {
		return puts(KEY_USER_ID, id);
	}
	/**set "userId{}":[] in Table layer
	 * @param list
	 * @return
	 */
	public JSONObject setUserIdIn(List<Object> list) {
		return puts(KEY_USER_ID_IN, list);
	}
	
	
	public static final int CACHE_ALL = 0;
	public static final int CACHE_ROM = 1;
	public static final int CACHE_RAM = 2;

	public static final String CACHE_ALL_STRING = "ALL";
	public static final String CACHE_ROM_STRING = "ROM";
	public static final String CACHE_RAM_STRING = "RAM";

	
	//@key关键字都放这个类 <<<<<<<<<<<<<<<<<<<<<<
	public static final String KEY_TRY = "@try"; //�?试，忽略异常
	public static final String KEY_DROP = "@drop"; //丢弃，�?返回
	public static final String KEY_CORRECT = "@correct"; //字段校正
	
	public static final String KEY_ROLE = "@role"; //角色，拥有对�?些数�?�的�?些�?作的�?��?
	public static final String KEY_DATABASE = "@database"; //数�?�库类型，默认为MySQL
	public static final String KEY_EXPLAIN = "@explain"; //分�? true/false
	public static final String KEY_CACHE = "@cache"; //缓存 RAM/ROM/ALL
	public static final String KEY_SCHEMA = "@schema"; //数�?�库，Table在�?�默认schema内时需�?声明
	public static final String KEY_COLUMN = "@column"; //查询的Table字段或SQL函数
	public static final String KEY_FROM = "@from"; //FROM语�?�
	public static final String KEY_COMBINE = "@combine"; //�?�件组�?�，�?个�?�件key�?�?��?�以放&,|,!逻辑关系  "id!{},&sex,!name&$"
	public static final String KEY_GROUP = "@group"; //分组方�?
	public static final String KEY_HAVING = "@having"; //�?��?�函数�?�件，一般和@group一起用
	public static final String KEY_ORDER = "@order"; //排�?方�?
	
	public static final List<String> TABLE_KEY_LIST;
	static {
		TABLE_KEY_LIST = new ArrayList<String>();
		TABLE_KEY_LIST.add(KEY_ROLE);
		TABLE_KEY_LIST.add(KEY_DATABASE);
		TABLE_KEY_LIST.add(KEY_EXPLAIN);
		TABLE_KEY_LIST.add(KEY_CACHE);
		TABLE_KEY_LIST.add(KEY_SCHEMA);
		TABLE_KEY_LIST.add(KEY_COLUMN);
		TABLE_KEY_LIST.add(KEY_FROM);
		TABLE_KEY_LIST.add(KEY_COMBINE);
		TABLE_KEY_LIST.add(KEY_GROUP);
		TABLE_KEY_LIST.add(KEY_HAVING);
		TABLE_KEY_LIST.add(KEY_ORDER);
	}

	//@key关键字都放这个类 >>>>>>>>>>>>>>>>>>>>>>


	/**set role of request sender
	 * @param role
	 * @return this
	 */
	public JSONObject setRole(String role) {
		return puts(KEY_ROLE, role);
	}
	
	/**set try, ignore exceptions
	 * @param tri
	 * @return this
	 */
	public JSONObject setTry(boolean tri) {
		return puts(KEY_TRY, tri);
	}
	
	/**set drop, data dropped will not return
	 * @param drop
	 * @return this
	 */
	public JSONObject setDrop(boolean drop) {
		return puts(KEY_DROP, drop);
	}
	
	/**set correct, correct keys to target ones
	 * @param correct  Map{originKey, [posibleKeys]}， posibleKey之间用 , 隔开
	 * @return this
	 */
	public JSONObject setCorrect(Map<String, String> correct) {
		return puts(KEY_CORRECT, correct);
	}
	
	

	/**set database where table was puts
	 * @param database
	 * @return this
	 */
	public JSONObject setDatabase(String database) {
		return puts(KEY_DATABASE, database);
	}
	/**set joins of Main Table and it's Vice Tables in Array layer
	 * @param joins "@/User/id@", "&/User/id@,>/Comment/momentId@" ...
	 * @return
	 */
	public JSONObject setExplain(boolean explain) {
		return puts(KEY_EXPLAIN, explain);
	}
	/**set cache type
	 * @param cache
	 * @return
	 * @see {@link #CACHE_ALL}
	 * @see {@link #CACHE_RAM}
	 * @see {@link #CACHE_ROM}
	 */
	public JSONObject setCache(int cache) {
		return puts(KEY_CACHE, cache);
	}
	/**set cache type
	 * @param cache
	 * @return
	 * @see {@link #CACHE_ALL_STRING}
	 * @see {@link #CACHE_RAM_STRING}
	 * @see {@link #CACHE_ROM_STRING}
	 */
	public JSONObject setCache(String cache) {
		return puts(KEY_CACHE, cache);
	}
	/**set schema where table was puts
	 * @param schema
	 * @return this
	 */
	public JSONObject setSchema(String schema) {
		return puts(KEY_SCHEMA, schema);
	}

	/**set keys need to be returned
	 * @param keys  key0, key1, key2 ...
	 * @return {@link #setColumn(String)}
	 */
	public JSONObject setColumn(String... keys) {
		return setColumn(StringUtil.getString(keys, true));
	}
	/**set keys need to be returned
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setColumn(String keys) {
		return puts(KEY_COLUMN, keys);
	}

	/**set combination of keys for conditions
	 * @param keys  key0,&key1,|key2,!kye3 ...
	 * @return {@link #setColumn(String)}
	 */
	public JSONObject setCombine(String... keys) {
		return setCombine(StringUtil.getString(keys, true));
	}
	/**set combination of keys for conditions
	 * @param keys  key0,&key1,|key2,!kye3 ...
	 * @return
	 */
	public JSONObject setCombine(String keys) {
		return puts(KEY_COMBINE, keys);
	}

	/**set keys for group by
	 * @param keys key0, key1, key2 ...
	 * @return {@link #setGroup(String)}
	 */
	public JSONObject setGroup(String... keys) {
		return setGroup(StringUtil.getString(keys, true));
	}
	/**set keys for group by
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setGroup(String keys) {
		return puts(KEY_GROUP, keys);
	}

	/**set keys for having
	 * @param keys count(key0) > 1, sum(key1) <= 5, function2(key2) ? value2 ...
	 * @return {@link #setHaving(String)}
	 */
	public JSONObject setHaving(String... keys) {
		return setHaving(StringUtil.getString(keys, true));
	}
	/**set keys for having
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setHaving(String keys) {
		return puts(KEY_HAVING, keys);
	}

	/**set keys for order by
	 * @param keys  key0, key1+, key2- ...
	 * @return {@link #setOrder(String)}
	 */
	public JSONObject setOrder(String... keys) {
		return setOrder(StringUtil.getString(keys, true));
	}
	/**set keys for order by
	 * @param keys  "key0,key1+,key2-..."
	 * @return
	 */
	public JSONObject setOrder(String keys) {
		return puts(KEY_ORDER, keys);
	}


	//JSONObject内关键�? key >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//Request <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**
	 * @param key
	 * @param keys  path = keys[0] + "/" + keys[1] + "/" + keys[2] + ...
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsPath(String key, String... keys) {
		return puts(key+"@", StringUtil.getString(keys, "/"));
	}

	/**
	 * @param key
	 * @param isNull
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsNull(String key, boolean isNull) {
		return puts(key+"{}", SQL.isNull(isNull));
	}
	/**
	 * trim = false
	 * @param key
	 * @param isEmpty
	 * @return {@link #putsEmpty(String, boolean, boolean)}
	 */
	public JSONObject putsEmpty(String key, boolean isEmpty) {
		return putsEmpty(key, isEmpty, false);
	}
	/**
	 * @param key
	 * @param isEmpty
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsEmpty(String key, boolean isEmpty, boolean trim) {
		return puts(key+"{}", SQL.isEmpty(key, isEmpty, trim));
	}
	/**
	 * @param key
	 * @param compare <=0, >5 ...
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsLength(String key, String compare) {
		return puts(key+"{}", SQL.length(key) + compare);
	}

	/**设置�?�索
	 * type = SEARCH_TYPE_CONTAIN_FULL
	 * @param key
	 * @param value
	 * @return {@link #putsSearch(String, String, int)}
	 */
	public JSONObject putsSearch(String key, String value) {
		return putsSearch(key, value, SQL.SEARCH_TYPE_CONTAIN_FULL);
	}
	/**设置�?�索
	 * @param key
	 * @param value
	 * @param type
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsSearch(String key, String value, int type) {
		return puts(key+"$", SQL.search(value, type));
	}

	//Request >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	

	/**puts key-value in object into this
	 * @param map
	 * @return this
	 */
	public JSONObject putsAll(Map<? extends String, ? extends Object> map) {
		putAll(map);
		return this;
	}
	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		if (map != null && map.isEmpty() == false) {
			super.putAll(map);
		}
	}



	/**put and return this
	 * @param value  must be annotated by {@link MethodAccess}
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject puts(Object value) {
		return puts(null, value);
	}
	/**put and return this
	 * @param key
	 * @param value 
	 * @return this
	 * @see {@link #put(String, Object)}
	 */
	public JSONObject puts(String key, Object value) {
		put(key, value);
		return this;
	}

	/**put and return value
	 * @param value  must be annotated by {@link MethodAccess}
	 * @return {@link #put(String, Object)}
	 */
	public Object put(Object value) {
		return put(null, value);
	}
	/**put and return value
	 * @param key  StringUtil.isEmpty(key, true) ? key = value.getClass().getSimpleName();
	 * @param value
	 * @return value
	 */
	@Override
	public Object put(String key, Object value) {
		if (value == null) {
			Log.e(TAG, "put  value == null >> return null;");
			return null;
		}
		if (StringUtil.isEmpty(key, true)) {
			Class<?> clazz = value.getClass(); //should not return null
			if (clazz.getAnnotation(MethodAccess.class) == null) {
				throw new IllegalArgumentException("puts  StringUtil.isEmpty(key, true)" +
						" clazz.getAnnotation(MethodAccess.class) == null" +
						" \n key为空时仅支�? 类型被@MethodAccess注解 的value !!!" +
						" \n 如果一定�?这么用，请对 " + clazz.getName() + " 注解�?" +
						" \n 如果是类似 key[]:{} 结构的请求，建议用 putsAll(...) �?");
			}
			key = value.getClass().getSimpleName();
		}
		return super.put(key, value);
	}


	
}
