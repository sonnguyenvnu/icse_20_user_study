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

package zuo.biao.apijson.server;

import static zuo.biao.apijson.JSONObject.KEY_COMBINE;
import static zuo.biao.apijson.JSONObject.KEY_CORRECT;
import static zuo.biao.apijson.JSONObject.KEY_DROP;
import static zuo.biao.apijson.JSONObject.KEY_TRY;
import static zuo.biao.apijson.RequestMethod.PUT;
import static zuo.biao.apijson.server.SQLConfig.TYPE_ITEM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zuo.biao.apijson.Log;
import zuo.biao.apijson.NotNull;
import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.StringUtil;
import zuo.biao.apijson.server.RemoteFunction.FunctionBean;
import zuo.biao.apijson.server.exception.ConflictException;
import zuo.biao.apijson.server.exception.NotExistException;


/**简化Parser，getObject和getArray(getArrayConfig)都能用
 * @author Lemon
 */
public abstract class AbstractObjectParser implements ObjectParser {
	private static final String TAG = "AbstractObjectParser";

	@NotNull
	protected Parser<?> parser;
	public AbstractObjectParser setParser(Parser<?> parser) {
		this.parser = parser;
		return this;
	}


	protected JSONObject request;//�?用final是为了recycle
	protected String parentPath;//�?用final是为了recycle
	protected SQLConfig arrayConfig;//�?用final是为了recycle
	protected boolean isSubquery;

	protected final int type;
	protected final List<Join> joinList;
	protected final boolean isTable;
	protected final String path;
	protected final String table;


	protected final boolean tri;
	/**
	 * TODO Parser内�?�?因为 �?� TYPE_ITEM_CHILD_0 的Table 为空导致�?�续中断。
	 */
	protected final boolean drop;
	protected final JSONObject correct;

	/**for single object
	 * @param parentPath
	 * @param request
	 * @param name
	 * @throws Exception 
	 */
	public AbstractObjectParser(@NotNull JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception {
		if (request == null) {
			throw new IllegalArgumentException(TAG + ".ObjectParser  request == null!!!");
		}
		this.request = request;
		this.parentPath = parentPath;
		this.arrayConfig = arrayConfig;
		this.isSubquery = isSubquery;

		this.type = arrayConfig == null ? 0 : arrayConfig.getType();
		this.joinList = arrayConfig == null ? null : arrayConfig.getJoinList();
		this.path = AbstractParser.getAbsPath(parentPath, name);
		this.table = Pair.parseEntry(name, true).getKey();
		this.isTable = zuo.biao.apijson.JSONObject.isTableKey(table);

		this.objectCount = 0;
		this.arrayCount = 0;

		boolean isEmpty = request.isEmpty();//empty有效 User:{}
		if (isEmpty) {
			this.tri = false;
			this.drop = false;
			this.correct = null;
		}
		else {
			this.tri = request.getBooleanValue(KEY_TRY);
			this.drop = request.getBooleanValue(KEY_DROP);
			this.correct = request.getJSONObject(KEY_CORRECT);

			request.remove(KEY_TRY);
			request.remove(KEY_DROP);
			request.remove(KEY_CORRECT);

			try {
				parseCorrect();
			} catch (Exception e) {
				if (tri == false) {
					throw e;
				}
				invalidate();
			}
		}


		Log.d(TAG, "AbstractObjectParser  table = " + table + "; isTable = " + isTable);
		Log.d(TAG, "AbstractObjectParser  isEmpty = " + isEmpty + "; tri = " + tri + "; drop = " + drop);
	}

	public static final Map<String, Pattern> COMPILE_MAP;
	static {
		COMPILE_MAP = new HashMap<String, Pattern>();
	}

	protected Map<String, String> corrected;
	/**解�? @correct 校正
	 * @throws Exception 
	 */
	@Override
	public AbstractObjectParser parseCorrect() throws Exception {
		Set<String> set = correct == null ? null : new HashSet<>(correct.keySet());

		if (set != null && set.isEmpty() == false) {//对�?个需�?校正的key进行正则表达�?匹�?校正
			corrected = new HashMap<>();//TODO 返回全部correct内的内容，包括未校正的?  correct);

			String value; //13000082001
			String v; // phone,email,idCard
			String[] posibleKeys; //[phone,email,idCard]

			for (String k : set) {// k = cert
				v = k == null ? null : correct.getString(k);
				value = v == null ? null : request.getString(k);
				posibleKeys = value == null ? null : StringUtil.split(v);

				if (posibleKeys != null && posibleKeys.length > 0) {
					String rk = null;
					Pattern p;
					for (String pk : posibleKeys) {
						p = pk == null ? null : COMPILE_MAP.get(pk);
						if (p != null && p.matcher(value).matches()) {
							rk = pk;
							break;
						}
					}

					if (rk == null) {
						throw new IllegalArgumentException("格�?错误�?找�?到 " + k + ":" + value + " 对应[" + v + "]内的任何一项�?");
					}
					request.put(rk, request.remove(k));
					corrected.put(k, rk);
				}
			}
		}

		return this;
	}



	private boolean invalidate = false;
	public void invalidate() {
		invalidate = true;
	}
	public boolean isInvalidate() {
		return invalidate;
	}

	private boolean breakParse = false;
	public void breakParse() {
		breakParse = true;
	}
	public boolean isBreakParse() {
		return breakParse || isInvalidate();
	}


	protected JSONObject response;
	protected JSONObject sqlRequest;
	protected JSONObject sqlReponse;
	/**
	 * 自定义关键�?
	 */
	protected Map<String, Object> customMap;
	/**
	 * 远程函数
	 * {"-":{ "key-()":value }, "0":{ "key()":value }, "+":{ "key+()":value } }
	 * - : 在executeSQL�?解�?
	 * 0 : 在executeSQL�?��?onChildParse�?解�?
	 * + : 在onChildParse�?�解�?
	 */
	protected Map<String, Map<String, String>> functionMap;
	/**
	 * �?对象
	 */
	protected Map<String, JSONObject> childMap;

	private int objectCount;
	private int arrayCount;
	/**解�?�?员
	 * response�?新赋值
	 * @return null or this
	 * @throws Exception
	 */
	@Override
	public AbstractObjectParser parse() throws Exception {
		if (isInvalidate() == false) {
			breakParse = false;

			response = new JSONObject(true);//must init

			sqlRequest = new JSONObject(true);//must init

			sqlReponse = null;//must init
			customMap = null;//must init
			functionMap = null;//must init
			childMap = null;//must init

			Set<Entry<String, Object>> set = new LinkedHashSet<Entry<String, Object>>(request.entrySet());
			if (set != null && set.isEmpty() == false) {//判断�?��?�少几个�?��?的�?始化是�?�值得？
				if (isTable) {//�?�Table下必须�?�?原有顺�?�?�?�则 count,page 会丢, total@:"/[]/total" 会在[]:{}�?执行�?
					customMap = new LinkedHashMap<String, Object>();
					childMap = new LinkedHashMap<String, JSONObject>();
				}
				functionMap = new LinkedHashMap<String, Map<String, String>>();//必须执行


				//�?�件<<<<<<<<<<<<<<<<<<<
				List<String> whereList = null;
				if (method == PUT) { //这里�?�有PUTArray需�?处�?�  || method == DELETE) {
					String[] combine = StringUtil.split(request.getString(KEY_COMBINE));
					if (combine != null) {
						String w;
						for (int i = 0; i < combine.length; i++) { //去除 &,|,! �?缀
							w = combine[i];
							if (w != null && (w.startsWith("&") || w.startsWith("|") || w.startsWith("!"))) {
								combine[i] = w.substring(1);
							}
						}
					}
					//Arrays.asList()返回值�?支�?add方法�?
					whereList = new ArrayList<String>(Arrays.asList(combine != null ? combine : new String[]{}));
					whereList.add(zuo.biao.apijson.JSONRequest.KEY_ID);
					whereList.add(zuo.biao.apijson.JSONRequest.KEY_ID_IN);
				}
				//�?�件>>>>>>>>>>>>>>>>>>>

				String key;
				Object value;
				int index = 0;

				for (Entry<String, Object> entry : set) {
					if (isBreakParse()) {
						break;
					}

					value = entry.getValue();
					if (value == null) {
						continue;
					}
					key = entry.getKey();

					try {
						if (value instanceof JSONObject && key.startsWith("@") == false && key.endsWith("@") == false) {//JSONObject，往下一级�??�?�
							if (childMap != null) {//添加到childMap，最�?��?解�?
								childMap.put(key, (JSONObject)value);
							}
							else {//直接解�?并替�?�原�?�的，[]:{} 内必须直接解�?，�?�则会因为丢掉count等属性，并且total@:"/[]/total"必须在[]:{} �?��?
								response.put(key, onChildParse(index, key, (JSONObject)value));
								index ++;
							}
						}
						else if (method == PUT && value instanceof JSONArray
								&& (whereList == null || whereList.contains(key) == false)) {//PUT JSONArray
							onPUTArrayParse(key, (JSONArray) value);
						}
						else {//JSONArray或其它Object，直接填充
							if (onParse(key, value) == false) {
								invalidate();
							}
						}
					} catch (Exception e) {
						if (tri == false) {
							throw e;//�?忽略错误，抛异常
						}
						invalidate();//忽略错误，还原request
					}
				}

				//�?�Table内的函数会被滞�?�在onChildParse�?�调用�? onFunctionResponse("-");
			}

			if (isTable) {
				if (sqlRequest.get(JSONRequest.KEY_DATABASE) == null && parser.getGlobleDatabase() != null) {
					sqlRequest.put(JSONRequest.KEY_DATABASE, parser.getGlobleDatabase());
				}
				if (sqlRequest.get(JSONRequest.KEY_SCHEMA) == null && parser.getGlobleSchema() != null) {
					sqlRequest.put(JSONRequest.KEY_SCHEMA, parser.getGlobleSchema());
				}
				if (sqlRequest.get(JSONRequest.KEY_EXPLAIN) == null && parser.getGlobleExplain() != null) {
					sqlRequest.put(JSONRequest.KEY_EXPLAIN, parser.getGlobleExplain());
				}
				if (sqlRequest.get(JSONRequest.KEY_CACHE) == null && parser.getGlobleCache() != null) {
					sqlRequest.put(JSONRequest.KEY_CACHE, parser.getGlobleCache());
				}
			}
		}

		if (isInvalidate()) {
			recycle();
			return null;
		}

		return this;
	}





	/**解�?普通�?员
	 * @param key
	 * @param value
	 * @return whether parse succeed
	 */
	@Override
	public boolean onParse(@NotNull String key, @NotNull Object value) throws Exception {
		if (key.endsWith("@")) {//StringUtil.isPath((String) value)) {

			if (value instanceof JSONObject) { // SQL �?查询对象，JSONObject -> SQLConfig.getSQL
				String replaceKey = key.substring(0, key.length() - 1);//key{}@ getRealKey

				JSONObject subquery = (JSONObject) value;
				String range = subquery.getString(JSONRequest.KEY_SUBQUERY_RANGE);
				if (range != null && JSONRequest.SUBQUERY_RANGE_ALL.equals(range) == false && JSONRequest.SUBQUERY_RANGE_ANY.equals(range) == false) {
					throw new IllegalArgumentException("�?查询 " + path + "/" + key + ":{ range:value } 中 value �?�能为 [" + JSONRequest.SUBQUERY_RANGE_ALL + ", " + JSONRequest.SUBQUERY_RANGE_ANY + "] 中的一个�?");
				}


				JSONArray arr = parser.onArrayParse(subquery, AbstractParser.getAbsPath(path, replaceKey), "[]", true);

				JSONObject obj = arr == null || arr.isEmpty() ? null : arr.getJSONObject(0);
				if (obj == null) {
					throw new Exception("�?务器内部错误，解�?�?查询 " + path + "/" + key + ":{ } 为 Subquery 对象失败�?");
				}

				String from = subquery.getString(JSONRequest.KEY_SUBQUERY_FROM);
				JSONObject arrObj = from == null ? null : obj.getJSONObject(from);
				if (arrObj == null) {
					throw new IllegalArgumentException("�?查询 " + path + "/" + key + ":{ from:value } 中 value 对应的主表对象 " + from + ":{} �?存在�?");
				}
				//				
				SQLConfig cfg = (SQLConfig) arrObj.get(AbstractParser.KEY_CONFIG);
				if (cfg == null) {
					throw new NotExistException(TAG + ".onParse  cfg == null");
				}

				Subquery s = new Subquery();
				s.setPath(path);
				s.setOriginKey(key);
				s.setOriginValue(subquery);

				s.setFrom(from);
				s.setRange(range);
				s.setKey(replaceKey);
				s.setConfig(cfg);

				key = replaceKey;
				value = s; //(range == null || range.isEmpty() ? "" : "range") + "(" + cfg.getSQL(false) + ") ";

				parser.putQueryResult(AbstractParser.getAbsPath(path, key), s); //字符串引用�?�?�?了安全性 parser.getSQL(cfg));
			}
			else if (value instanceof String) { // 引用赋值路径

				//						System.out.println("getObject  key.endsWith(@) >> parseRelation = " + parseRelation);
				String replaceKey = key.substring(0, key.length() - 1);//key{}@ getRealKey
				String targetPath = AbstractParser.getValuePath(type == TYPE_ITEM
						? path : parentPath, new String((String) value));

				//先�?试获�?�，尽�?�?留缺�?�?赖路径，这样就�?需�?担心路径改�?�
				Object target = onReferenceParse(targetPath);
				Log.i(TAG, "onParse targetPath = " + targetPath + "; target = " + target);

				if (target == null) {//String#equals(null)会出错
					Log.d(TAG, "onParse  target == null  >>  continue;");
					return true;
				}
				if (target instanceof Map) { //target�?�能是从requestObject里�?�出的 {}
					Log.d(TAG, "onParse  target instanceof Map  >>  continue;");
					return false;
				}
				if (targetPath.equals(target)) {//必须valuePath和�?�?getValueByPath传进去的一致�?
					Log.d(TAG, "onParse  targetPath.equals(target)  >>");

					//�?�查询关键�? @key �?影�?查询，直接跳过
					if (isTable && (key.startsWith("@") == false || JSONRequest.TABLE_KEY_LIST.contains(key))) {
						Log.e(TAG, "onParse  isTable && (key.startsWith(@) == false"
								+ " || JSONRequest.TABLE_KEY_LIST.contains(key)) >>  return null;");
						return false;//获�?��?到就�?用�?�?�无效的query了。�?考虑 Table:{Table:{}}嵌套
					} else {
						Log.d(TAG, "onParse  isTable(table) == false >> continue;");
						return true;//�?去，对Table无影�?
					}
				} 

				//直接替�?�原�?�的key@:path为key:target
				Log.i(TAG, "onParse    >>  key = replaceKey; value = target;");
				key = replaceKey;
				value = target;
				Log.d(TAG, "onParse key = " + key + "; value = " + value);
			}
			else {
				throw new IllegalArgumentException(path + "/" + key + ":value 中 value 必须为 �?赖路径String 或 SQL�?查询JSONObject �?");
			}

		}

		if (key.endsWith("()")) {
			if (value instanceof String == false) {
				throw new IllegalArgumentException(path + "/" + key + ":value 中 value 必须为函数String�?");
			}

			String k = key.substring(0, key.length() - 2);

			String type; //远程函数比较少用，一般一个Table:{}内用到也就一两个，所以这里用 "-","0","+" 更直观，转用 -1,0,1 对性能�??�?��?大。
			if (k.endsWith("-")) { //�?能�?装到functionMap�?�批�?执行，�?�则会导致�?�Table内的 key-():function() 在onChildParse�?�执行�?
				type = "-";
				k = k.substring(0, k.length() - 1);

				parseFunction(request, k, (String) value);
			}
			else {
				if (k.endsWith("+")) {
					type = "+";
					k = k.substring(0, k.length() - 1);
				}
				else {
					type = "0";
				}

				//远程函数比较少用，一般一个Table:{}内用到也就一两个，所以这里循环里new出�?�对性能影�?�?大。
				Map<String, String> map = functionMap.get(type);
				if (map == null) {
					map = new LinkedHashMap<>();
				}
				map.put(k, (String) value);

				functionMap.put(type, map);
			}
		}
		else if (isTable && key.startsWith("@") && JSONRequest.TABLE_KEY_LIST.contains(key) == false) {
			customMap.put(key, value);
		}
		else {
			sqlRequest.put(key, value);
		}

		return true;
	}



	/**
	 * @param key
	 * @param value
	 * @param isFirst 
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSON onChildParse(int index, String key, JSONObject value) throws Exception {
		boolean isFirst = index <= 0;
		boolean isMain = isFirst && type == TYPE_ITEM;

		JSON child;
		boolean isEmpty;

		if (zuo.biao.apijson.JSONObject.isArrayKey(key)) {//APIJSON Array
			if (isMain) {
				throw new IllegalArgumentException(parentPath + "/" + key + ":{} �?�?�法�?"
						+ "数组 []:{} 中第一个 key:{} 必须是主表 TableKey:{} �?�?能为 arrayKey[]:{} �?");
			}

			if (arrayConfig == null || arrayConfig.getPosition() == 0) {
				arrayCount ++;
				int maxArrayCount = parser.getMaxArrayCount();
				if (arrayCount > maxArrayCount) {
					throw new IllegalArgumentException(path + " 内截至 " + key + ":{} 时数组对象 key[]:{} 的数�?达到 " + arrayCount + " 已超�?，必须在 0-" + maxArrayCount + " 内 !");
				}
			}

			child = parser.onArrayParse(value, path, key, isSubquery);
			isEmpty = child == null || ((JSONArray) child).isEmpty();
		}
		else {//APIJSON Object
			boolean isTableKey = JSONRequest.isTableKey(Pair.parseEntry(key, true).getKey());
			if (type == TYPE_ITEM && isTableKey == false) {
				throw new IllegalArgumentException(parentPath + "/" + key + ":{} �?�?�法�?"
						+ "数组 []:{} 中�?个 key:{} 都必须是表 TableKey:{} 或 数组 arrayKey[]:{} �?");
			}

			if (//�?��?使用 "test":{"Test":{}} 绕过�?制，实现查询爆炸   isTableKey && 
					(arrayConfig == null || arrayConfig.getPosition() == 0)) {
				objectCount ++;
				int maxObjectCount = parser.getMaxObjectCount();
				if (objectCount > maxObjectCount) {
					throw new IllegalArgumentException(path + " 内截至 " + key + ":{} 时对象 key:{} 的数�?达到 " + objectCount + " 已超�?，必须在 0-" + maxObjectCount + " 内 !");
				}
			}

			child = parser.onObjectParse(value, path, key, isMain ? arrayConfig.setType(SQLConfig.TYPE_ITEM_CHILD_0) : null, isSubquery);

			isEmpty = child == null || ((JSONObject) child).isEmpty();
			if (isFirst && isEmpty) {
				invalidate();
			}
		}
		Log.i(TAG, "onChildParse  ObjectParser.onParse  key = " + key + "; child = " + child);

		return isEmpty ? null : child;//�?�添加! isChildEmpty的值，�?�能数�?�库返回数�?��?够count
	}



	//TODO 改用 MySQL json_add,json_remove,json_contains 等函数�? 
	/**PUT key:[]
	 * @param key
	 * @param array
	 * @throws Exception
	 */
	@Override
	public void onPUTArrayParse(@NotNull String key, @NotNull JSONArray array) throws Exception {
		if (isTable == false || array.isEmpty()) {
			Log.e(TAG, "onPUTArrayParse  isTable == false || array == null || array.isEmpty() >> return;");
			return;
		}

		int putType = 0;
		if (key.endsWith("+")) {//add
			putType = 1;
		} else if (key.endsWith("-")) {//remove
			putType = 2;
		} else {//replace
			//			throw new IllegalAccessException("PUT " + path + ", PUT Array�?�?许 " + key + 
			//					" 这�?没有 + 或 - 结尾的key�?�?�?许整个替�?�掉原�?�的Array�?");
		}
		String realKey = AbstractSQLConfig.getRealKey(method, key, false, false, "`"); //FIXME PG 是 "

		//GET > add all 或 remove all > PUT > remove key

		//GET <<<<<<<<<<<<<<<<<<<<<<<<<
		JSONObject rq = new JSONObject();
		rq.put(JSONRequest.KEY_ID, request.get(JSONRequest.KEY_ID));
		rq.put(JSONRequest.KEY_COLUMN, realKey);
		JSONObject rp = parseResponse(new JSONRequest(table, rq));
		//GET >>>>>>>>>>>>>>>>>>>>>>>>>


		//add all 或 remove all <<<<<<<<<<<<<<<<<<<<<<<<<
		if (rp != null) {
			rp = rp.getJSONObject(table);
		}
		JSONArray targetArray = rp == null ? null : rp.getJSONArray(realKey);
		if (targetArray == null) {
			targetArray = new JSONArray();
		}
		for (Object obj : array) {
			if (obj == null) {
				continue;
			}
			if (putType == 1) {
				if (targetArray.contains(obj)) {
					throw new ConflictException("PUT " + path + ", " + realKey + ":" + obj + " 已存在�?");
				}
				targetArray.add(obj);
			} else if (putType == 2) {
				if (targetArray.contains(obj) == false) {
					throw new NullPointerException("PUT " + path + ", " + realKey + ":" + obj + " �?存在�?");
				}
				targetArray.remove(obj);
			}
		}

		//add all 或 remove all >>>>>>>>>>>>>>>>>>>>>>>>>

		//PUT <<<<<<<<<<<<<<<<<<<<<<<<<
		sqlRequest.put(realKey, targetArray);
		//PUT >>>>>>>>>>>>>>>>>>>>>>>>>

	}

	/**SQL �?置，for single object
	 * @return {@link #setSQLConfig(int, int, int)}
	 * @throws Exception
	 */
	@Override
	public AbstractObjectParser setSQLConfig() throws Exception {
		return setSQLConfig(1, 0, 0);
	}

	@Override
	public AbstractObjectParser setSQLConfig(int count, int page, int position) throws Exception {
		if (isTable == false) {
			return this;
		}

		if (sqlConfig == null) {
			try {
				sqlConfig = newSQLConfig(false);
			}
			catch (NotExistException e) {
				e.printStackTrace();
				return this;
			}
		}
		sqlConfig.setCount(count).setPage(page).setPosition(position);

		parser.onVerifyRole(sqlConfig);

		return this;
	}




	protected SQLConfig sqlConfig = null;//array item�?用
	/**SQL查询，for array item
	 * @param count
	 * @param page
	 * @param position
	 * @return this
	 * @throws Exception
	 */
	@Override
	public AbstractObjectParser executeSQL() throws Exception {
		//执行SQL�?作数�?�库
		if (isTable == false) {//�??高性能
			sqlReponse = new JSONObject(sqlRequest);
		} else {
			try {
				sqlReponse = onSQLExecute();
			}
			catch (NotExistException e) {
				//				Log.e(TAG, "getObject  try { response = getSQLObject(config2); } catch (Exception e) {");
				//				if (e instanceof NotExistException) {//�?�严�?异常，有时候�?�是数�?��?存在
				//					//						e.printStackTrace();
				sqlReponse = null;//内部�?�掉异常，put到最外层
				//						requestObject.put(JSONResponse.KEY_MSG
				//								, StringUtil.getString(requestObject.get(JSONResponse.KEY_MSG)
				//										+ "; query " + path + " cath NotExistException:"
				//										+ newErrorResult(e).getString(JSONResponse.KEY_MSG)));
				//				} else {
				//					throw e;
				//				}
			}

			if (drop) {//丢弃Table，�?�为了�?�下�??供�?�件
				sqlReponse = null;
			}
		}

		return this;
	}

	/**
	 * @return response
	 * @throws Exception
	 */
	@Override
	public JSONObject response() throws Exception {
		if (sqlReponse == null || sqlReponse.isEmpty()) {
			if (isTable) {//Table自身都获�?��?到值，则里�?�的Child都无�?义，�?需�?�?解�?
				return response;
			}
		} else {
			response.putAll(sqlReponse);
		}


		//把已校正的字段键值对corrected<originKey, correctedKey>添加进�?�，还是correct直接改？
		if (corrected != null) {
			response.put(KEY_CORRECT, corrected);
		}

		//把isTable时�?�出去的custom�?新添加回�?�
		if (customMap != null) {
			response.putAll(customMap);
		}


		onFunctionResponse("0");

		onChildResponse();

		onFunctionResponse("+");

		onComplete();

		return response;
	}


	@Override
	public void onFunctionResponse(String type) throws Exception {
		Map<String, String> map = functionMap == null ? null : functionMap.get(type);

		//解�?函数function
		Set<Entry<String, String>> functionSet = map == null ? null : map.entrySet();
		if (functionSet != null && functionSet.isEmpty() == false) {
			//			JSONObject json = "-".equals(type) ? request : response; // key-():function 是实时执行，而�?是在这里批�?执行

			for (Entry<String, String> entry : functionSet) {

				//				parseFunction(json, entry.getKey(), entry.getValue());
				parseFunction(response, entry.getKey(), entry.getValue());
			}
		}
	}

	public void parseFunction(JSONObject json, String key, String value) throws Exception {
		Object result;
		if (key.startsWith("@")) { //TODO 以�?�这�?�?众功能从 ORM 移出，作为一个 plugin/APIJSONProcedure
			FunctionBean fb = RemoteFunction.parseFunction(value, json, true);
			
			SQLConfig config = newSQLConfig(true);
			config.setProcedure(fb.toFunctionCallString(true));

			SQLExecutor executor = null;
			try {
				executor = parser.getSQLExecutor();
				result = executor.execute(config, true);
			}
			catch (NotExistException e) {
				e.printStackTrace();
				return;
			}
		}
		else {
			result = parser.onFunctionParse(json, value);
		}

		if (result != null) {
			String k = AbstractSQLConfig.getRealKey(method, key, false, false, "`"); //FIXME PG 是 "

			response.put(k, result);
			parser.putQueryResult(AbstractParser.getAbsPath(path, k), result);
		}
	}

	@Override
	public void onChildResponse() throws Exception {
		//把isTable时�?�出去child解�?�?��?新添加回�?�
		Set<Entry<String, JSONObject>> set = childMap == null ? null : childMap.entrySet();
		if (set != null) {
			int index = 0;
			for (Entry<String, JSONObject> entry : set) {
				if (entry != null) {
					response.put(entry.getKey(), onChildParse(index, entry.getKey(), entry.getValue()));
					index ++;
				}
			}
		}
	}



	@Override
	public Object onReferenceParse(@NotNull String path) {
		return parser.getValueByPath(path);
	}

	@Override
	public JSONObject onSQLExecute() throws Exception {
		JSONObject result = parser.executeSQL(sqlConfig, isSubquery);
		if (isSubquery == false && result != null) {
			parser.putQueryResult(path, result);//解决获�?�关�?�数�?�时requestObject里�?存在需�?的关�?�数�?�
		}
		return result;
	}


	/**
	 * response has the final value after parse (and query if isTable)
	 */
	@Override
	public void onComplete() {
	}


	/**回收内存
	 */
	@Override
	public void recycle() {
		//�?��?�还�?�能用到，�?还原
		if (tri) {//�?��?返回未传的字段
			request.put(KEY_TRY, tri);
		}
		if (drop) {
			request.put(KEY_DROP, drop);
		}
		if (correct != null) {
			request.put(KEY_CORRECT, correct);
		}


		corrected = null;
		method = null;
		parentPath = null;
		arrayConfig = null;

		//		if (response != null) {
		//			response.clear();//有效果?
		//			response = null;
		//		}

		request = null;
		response = null;
		sqlRequest = null;
		sqlReponse = null;

		functionMap = null;
		customMap = null;
		childMap = null;
	}






	protected RequestMethod method;
	@Override
	public AbstractObjectParser setMethod(RequestMethod method) {
		if (this.method != method) {
			this.method = method;
			sqlConfig = null;
			//TODO ?			sqlReponse = null;
		}
		return this;
	}
	@Override
	public RequestMethod getMethod() {
		return method;
	}




	@Override
	public boolean isTable() {
		return isTable;
	}
	@Override
	public String getPath() {
		return path;
	}
	@Override
	public String getTable() {
		return table;
	}
	@Override
	public SQLConfig getArrayConfig() {
		return arrayConfig;
	}


	@Override
	public SQLConfig getSQLConfig() {
		return sqlConfig;
	}

	@Override
	public JSONObject getResponse() {
		return response;
	}
	@Override
	public JSONObject getSqlRequest() {
		return sqlRequest;
	}
	@Override
	public JSONObject getSqlReponse() {
		return sqlReponse;
	}

	@Override
	public Map<String, Object> getCustomMap() {
		return customMap;
	}
	@Override
	public Map<String, Map<String, String>> getFunctionMap() {
		return functionMap;
	}
	@Override
	public Map<String, JSONObject> getChildMap() {
		return childMap;
	}


}
