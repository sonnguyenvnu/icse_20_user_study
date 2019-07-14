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

import static zuo.biao.apijson.JSONObject.KEY_EXPLAIN;
import static zuo.biao.apijson.RequestMethod.GET;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.activation.UnsupportedDataTypeException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zuo.biao.apijson.JSON;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.Log;
import zuo.biao.apijson.NotNull;
import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.RequestRole;
import zuo.biao.apijson.StringUtil;
import zuo.biao.apijson.server.exception.ConditionErrorException;
import zuo.biao.apijson.server.exception.ConflictException;
import zuo.biao.apijson.server.exception.NotExistException;
import zuo.biao.apijson.server.exception.NotLoggedInException;
import zuo.biao.apijson.server.exception.OutOfRangeException;

/**parser for parsing request to JSONObject
 * @author Lemon
 */
public abstract class AbstractParser<T> implements Parser<T>, SQLCreator {
	protected static final String TAG = "AbstractParser";


	/**
	 * GET
	 */
	public AbstractParser() {
		this(null);
	}
	/**
	 * @param requestMethod null ? requestMethod = GET
	 */
	public AbstractParser(RequestMethod method) {
		this(method, false);
	}


	/**
	 * @param requestMethod null ? requestMethod = GET
	 * @param noVerify 仅�?于为�?务端�??供方法�?验�?特�?�，普通请求�?�?设置为true�? 如果对应Table有�?��?也建议用默认值false，�?�?和客户端�?��?一致
	 */
	public AbstractParser(RequestMethod method, boolean noVerify) {
		super();
		setMethod(method);
		setNoVerify(noVerify);
	}

	@NotNull
	protected Visitor<T> visitor;
	@NotNull
	@Override
	public Visitor<T> getVisitor() {
		if (visitor == null) {
			visitor = new Visitor<T>() {

				@Override
				public T getId() {
					return null;
				}

				@Override
				public List<T> getContactIdList() {
					return null;
				}
			};
		}
		return visitor;
	}
	@Override
	public AbstractParser<T> setVisitor(@NotNull Visitor<T> visitor) {
		this.visitor = visitor;
		return this;
	}

	protected RequestMethod requestMethod;
	@NotNull
	@Override
	public RequestMethod getMethod() {
		return requestMethod;
	}
	@NotNull
	@Override
	public AbstractParser<T> setMethod(RequestMethod method) {
		this.requestMethod = method == null ? GET : method;
		return this;
	}

	protected JSONObject requestObject;
	@Override
	public JSONObject getRequest() {
		return requestObject;
	}
	@Override
	public AbstractParser<T> setRequest(JSONObject request) {
		this.requestObject = request;
		return this;
	}

	protected Boolean globleFormat;
	public AbstractParser<T> setGlobleFormat(Boolean globleFormat) {
		this.globleFormat = globleFormat;
		return this;
	}
	@Override
	public Boolean getGlobleFormat() {
		return globleFormat;
	}
	protected RequestRole globleRole;
	public AbstractParser<T> setGlobleRole(RequestRole globleRole) {
		this.globleRole = globleRole;
		return this;
	}
	@Override
	public RequestRole getGlobleRole() {
		return globleRole;
	}
	protected String globleDatabase;
	public AbstractParser<T> setGlobleDatabase(String globleDatabase) {
		this.globleDatabase = globleDatabase;
		return this;
	}
	@Override
	public String getGlobleDatabase() {
		return globleDatabase;
	}
	protected String globleSchema;
	public AbstractParser<T> setGlobleSchema(String globleSchema) {
		this.globleSchema = globleSchema;
		return this;
	}
	@Override
	public String getGlobleSchema() {
		return globleSchema;
	}
	protected Boolean globleExplain;
	public AbstractParser<T> setGlobleExplain(Boolean globleExplain) {
		this.globleExplain = globleExplain;
		return this;
	}
	@Override
	public Boolean getGlobleExplain() {
		return globleExplain;
	}
	protected String globleCache;
	public AbstractParser<T> setGlobleCache(String globleCache) {
		this.globleCache = globleCache;
		return this;
	}
	@Override
	public String getGlobleCache() {
		return globleCache;
	}

	@Override
	public boolean isNoVerify() {
		return noVerifyLogin && noVerifyRole && noVerifyContent;
	}
	@Override
	public AbstractParser<T> setNoVerify(boolean noVerify) {
		setNoVerifyLogin(noVerify);
		setNoVerifyRole(noVerify);
		setNoVerifyContent(noVerify);
		return this;
	}

	protected boolean noVerifyLogin;
	@Override
	public boolean isNoVerifyLogin() {
		return noVerifyLogin;
	}
	@Override
	public AbstractParser<T> setNoVerifyLogin(boolean noVerifyLogin) {
		this.noVerifyLogin = noVerifyLogin;
		return this;
	}
	protected boolean noVerifyRole;
	@Override
	public boolean isNoVerifyRole() {
		return noVerifyRole;
	}
	@Override
	public AbstractParser<T> setNoVerifyRole(boolean noVerifyRole) {
		this.noVerifyRole = noVerifyRole;
		return this;
	}
	protected boolean noVerifyContent;
	@Override
	public boolean isNoVerifyContent() {
		return noVerifyContent;
	}
	@Override
	public AbstractParser<T> setNoVerifyContent(boolean noVerifyContent) {
		this.noVerifyContent = noVerifyContent;
		return this;
	}





	protected SQLExecutor sqlExecutor;
	protected Verifier<T> verifier;
	protected Map<String, Object> queryResultMap;//path-result

	@Override
	public SQLExecutor getSQLExecutor() {
		return sqlExecutor;
	}
	@Override
	public Verifier<T> getVerifier() {
		return verifier;
	}

	/**解�?请求json并获�?�对应结果
	 * @param request
	 * @return
	 */
	@Override
	public String parse(String request) {
		return JSON.toJSONString(parseResponse(request));
	}
	/**解�?请求json并获�?�对应结果
	 * @param request
	 * @return
	 */
	@NotNull
	@Override
	public String parse(JSONObject request) {
		return JSON.toJSONString(parseResponse(request));
	}

	/**解�?请求json并获�?�对应结果
	 * @param request 先parseRequest中URLDecoder.decode(request, UTF_8);�?parseResponse(getCorrectRequest(...))
	 * @return parseResponse(requestObject);
	 */
	@NotNull
	@Override
	public JSONObject parseResponse(String request) {
		Log.d(TAG, "\n\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"
				+ requestMethod + "/parseResponse  request = \n" + request + "\n\n");

		try {
			requestObject = parseRequest(request);
		} catch (Exception e) {
			return newErrorResult(e);
		}

		return parseResponse(requestObject);
	}

	private int queryDepth;

	/**解�?请求json并获�?�对应结果
	 * @param request
	 * @return requestObject
	 */
	@NotNull
	@Override
	public JSONObject parseResponse(JSONObject request) {
		long startTime = System.currentTimeMillis();
		Log.d(TAG, "parseResponse  startTime = " + startTime
				+ "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n\n ");

		requestObject = request;

		verifier = createVerifier().setVisitor(getVisitor());

		if (RequestMethod.isPublicMethod(requestMethod) == false) {
			try {
				if (noVerifyLogin == false) {
					onVerifyLogin();
				}
				if (noVerifyContent == false) {
					onVerifyContent();
				}
			} catch (Exception e) {
				return extendErrorResult(requestObject, e);
			}
		}

		//必须在parseCorrectRequest�?��?�，因为parseCorrectRequest�?�能会添加 @role
		if (noVerifyRole == false && globleRole == null) {
			try {
				setGlobleRole(RequestRole.get(requestObject.getString(JSONRequest.KEY_ROLE)));
				requestObject.remove(JSONRequest.KEY_ROLE);
			} catch (Exception e) {
				return extendErrorResult(requestObject, e);
			}
		}

		try {
			setGlobleFormat(requestObject.getBoolean(JSONRequest.KEY_FORMAT));
			setGlobleDatabase(requestObject.getString(JSONRequest.KEY_DATABASE));
			setGlobleSchema(requestObject.getString(JSONRequest.KEY_SCHEMA));
			setGlobleExplain(requestObject.getBoolean(JSONRequest.KEY_EXPLAIN));
			setGlobleCache(requestObject.getString(JSONRequest.KEY_CACHE));

			requestObject.remove(JSONRequest.KEY_FORMAT);
			requestObject.remove(JSONRequest.KEY_DATABASE);
			requestObject.remove(JSONRequest.KEY_SCHEMA);
			requestObject.remove(JSONRequest.KEY_EXPLAIN);
			requestObject.remove(JSONRequest.KEY_CACHE);
		} catch (Exception e) {
			return extendErrorResult(requestObject, e);
		}

		final String requestString = JSON.toJSONString(request);//request传进去解�?�?�已�?�?�了


		queryResultMap = new HashMap<String, Object>();

		Exception error = null;
		sqlExecutor = createSQLExecutor();
		try {
			queryDepth = 0;
			requestObject = onObjectParse(request, null, null, null, false);
		} catch (Exception e) {
			e.printStackTrace();
			error = e;
		}

		requestObject = error == null ? extendSuccessResult(requestObject) : extendErrorResult(requestObject, error);

		JSONObject res = (globleFormat != null && globleFormat) && JSONResponse.isSuccess(requestObject) ? new JSONResponse(requestObject) : requestObject;

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		if (Log.DEBUG) {
			requestObject.put("sql:generate/cache/execute/maxExecute", sqlExecutor.getGeneratedSQLCount() + "/" + sqlExecutor.getCachedSQLCount() + "/" + sqlExecutor.getExecutedSQLCount() + "/" + getMaxSQLCount());
			requestObject.put("depth:count/max", queryDepth + "/" + getMaxQueryDepth());
			requestObject.put("time:start/duration/end", startTime + "/" + duration + "/" + endTime);
		}

		sqlExecutor.close();
		sqlExecutor = null;
		queryResultMap.clear();
		queryResultMap = null;

		//会�?会导致原�?�的session = null？		session = null;

		if (Log.DEBUG) {
			Log.d(TAG, "\n\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n "
					+ requestMethod + "/parseResponse  request = \n" + requestString + "\n\n");

			Log.d(TAG, "parseResponse  return response = \n" + JSON.toJSONString(requestObject)
			+ "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n\n\n");
		}
		Log.d(TAG, "parseResponse  endTime = " + endTime + ";  duration = " + duration
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n\n");

		return res;
	}


	@Override
	public void onVerifyLogin() throws Exception {
		verifier.verifyLogin();
	}
	@Override
	public void onVerifyContent() throws Exception {
		requestObject = parseCorrectRequest();
	}
	/**校验角色�?�对应�?作的�?��?
	 * @param config
	 * @return
	 * @throws Exception
	 */
	@Override
	public void onVerifyRole(@NotNull SQLConfig config) throws Exception {
		if (Log.DEBUG) {
			Log.i(TAG, "onVerifyRole  config = " + JSON.toJSONString(config));
		}

		if (noVerifyRole == false) {
			if (config.getRole() == null) {
				if (globleRole != null) {
					config.setRole(globleRole);
				} else {
					config.setRole(getVisitor().getId() == null ? RequestRole.UNKNOWN : RequestRole.LOGIN);
				}
			}
			verifier.verify(config);
		}

	}


	/**解�?请求JSONObject
	 * @param request => URLDecoder.decode(request, UTF_8);
	 * @return
	 * @throws Exception 
	 */
	@NotNull
	public static JSONObject parseRequest(String request) throws Exception {
		JSONObject obj = JSON.parseObject(request);
		if (obj == null) {
			throw new UnsupportedEncodingException("JSON格�?�?�?�法�?");
		}
		return obj;
	}

	@Override
	public JSONObject parseCorrectRequest(JSONObject target) throws Exception {
		return Structure.parseRequest(requestMethod, "", target, requestObject, getMaxUpdateCount(), this);
	}


	/**新建带状�?内容的JSONObject
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JSONObject newResult(int code, String msg) {
		return extendResult(null, code, msg);
	}
	/**添加JSONObject的状�?内容，一般用于错误�??示结果
	 * @param object
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JSONObject extendResult(JSONObject object, int code, String msg) {
		if (object == null) {
			object = new JSONObject(true);
		}
		if (object.containsKey(JSONResponse.KEY_CODE) == false) {
			object.put(JSONResponse.KEY_CODE, code);
		}
		String m = StringUtil.getString(object.getString(JSONResponse.KEY_MSG));
		if (m.isEmpty() == false) {
			msg = m + " ;\n " + StringUtil.getString(msg);
		}
		object.put(JSONResponse.KEY_MSG, msg);
		return object;
	}


	/**添加请求�?功的状�?内容
	 * @param object
	 * @return
	 */
	public static JSONObject extendSuccessResult(JSONObject object) {
		return extendResult(object, JSONResponse.CODE_SUCCESS, JSONResponse.MSG_SUCCEED);
	}
	/**获�?�请求�?功的状�?内容
	 * @return
	 */
	public static JSONObject newSuccessResult() {
		return newResult(JSONResponse.CODE_SUCCESS, JSONResponse.MSG_SUCCEED);
	}
	/**添加请求�?功的状�?内容
	 * @param object
	 * @return
	 */
	public static JSONObject extendErrorResult(JSONObject object, Exception e) {
		JSONObject error = newErrorResult(e);
		return extendResult(object, error.getIntValue(JSONResponse.KEY_CODE), error.getString(JSONResponse.KEY_MSG));
	}
	/**新建错误状�?内容
	 * @param e
	 * @return
	 */
	public static JSONObject newErrorResult(Exception e) {
		if (e != null) {
			e.printStackTrace();

			int code;
			if (e instanceof UnsupportedEncodingException) {
				code = JSONResponse.CODE_UNSUPPORTED_ENCODING;
			} 
			else if (e instanceof IllegalAccessException) {
				code = JSONResponse.CODE_ILLEGAL_ACCESS;
			}
			else if (e instanceof UnsupportedOperationException) {
				code = JSONResponse.CODE_UNSUPPORTED_OPERATION;
			}
			else if (e instanceof NotExistException) {
				code = JSONResponse.CODE_NOT_FOUND;
			}
			else if (e instanceof IllegalArgumentException) {
				code = JSONResponse.CODE_ILLEGAL_ARGUMENT;
			}
			else if (e instanceof NotLoggedInException) {
				code = JSONResponse.CODE_NOT_LOGGED_IN;
			}
			else if (e instanceof TimeoutException) {
				code = JSONResponse.CODE_TIME_OUT;
			} 
			else if (e instanceof ConflictException) {
				code = JSONResponse.CODE_CONFLICT;
			}
			else if (e instanceof ConditionErrorException) {
				code = JSONResponse.CODE_CONDITION_ERROR;
			}
			else if (e instanceof UnsupportedDataTypeException) {
				code = JSONResponse.CODE_UNSUPPORTED_TYPE;
			}
			else if (e instanceof OutOfRangeException) {
				code = JSONResponse.CODE_OUT_OF_RANGE;
			}
			else if (e instanceof NullPointerException) {
				code = JSONResponse.CODE_NULL_POINTER;
			}
			else {
				code = JSONResponse.CODE_SERVER_ERROR;
			}

			return newResult(code, e.getMessage());
		}

		return newResult(JSONResponse.CODE_SERVER_ERROR, JSONResponse.MSG_SERVER_ERROR);
	}




	//TODO �?�动时一次性加载Request所有内容，作为�?始化。
	/**获�?�正确的请求，�?�GET请求必须是�?务器指定的
	 * @param method
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Override
	public JSONObject parseCorrectRequest() throws Exception {
		if (RequestMethod.isPublicMethod(requestMethod)) {
			return requestObject;//需�?指定JSON结构的get请求�?�以改为post请求。一般�?�有对安全性�?求高的�?会指定，而这�?情况用明文的GET方�?几乎肯定�?安全
		}

		String tag = requestObject.getString(JSONRequest.KEY_TAG);
		if (StringUtil.isNotEmpty(tag, true) == false) {
			throw new IllegalArgumentException("请在最外层设置tag�?一般是Table�??，例如 \"tag\": \"User\" ");
		}
		int version = requestObject.getIntValue(JSONRequest.KEY_VERSION);

		JSONObject object = null;
		String error = "";
		try {
			object = getStructure("Request", JSONRequest.KEY_TAG, tag, version);
		} catch (Exception e) {
			error = e.getMessage();
		}
		if (object == null) {//empty表示�?�?�?作  || object.isEmpty()) {
			throw new UnsupportedOperationException("�?�开放请求必须是Request表中校验规则�?许的�?作�?\n " + error);
		}

		JSONObject target = null;
		if (zuo.biao.apijson.JSONObject.isTableKey(tag) && object.containsKey(tag) == false) {//tag是table�??
			target = new JSONObject(true);
			target.put(tag, object);
		} else {
			target = object;
		}
		//获�?�指定的JSON结构 >>>>>>>>>>>>>>

		requestObject.remove(JSONRequest.KEY_TAG);
		requestObject.remove(JSONRequest.KEY_VERSION);
		return parseCorrectRequest((JSONObject) target.clone());
	}


	//TODO 优化性能�?
	/**获�?�正确的返回结果
	 * @param method
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@Override
	public JSONObject parseCorrectResponse(String table, JSONObject response) throws Exception {
		//		Log.d(TAG, "getCorrectResponse  method = " + method + "; table = " + table);
		//		if (response == null || response.isEmpty()) {//�?��?无效空result:{}添加内容�?��?�有效
		//			Log.e(TAG, "getCorrectResponse  response == null || response.isEmpty() >> return response;");
		return response;
		//		}
		//
		//		JSONObject target = zuo.biao.apijson.JSONObject.isTableKey(table) == false
		//				? new JSONObject() : getStructure(method, "Response", "model", table);
		//
		//				return MethodStructure.parseResponse(method, table, target, response, new OnParseCallback() {
		//
		//					@Override
		//					protected JSONObject onParseJSONObject(String key, JSONObject tobj, JSONObject robj) throws Exception {
		//						return getCorrectResponse(method, key, robj);
		//					}
		//				});
	}

	/**获�?�Request或Response内指定JSON结构
	 * @param table
	 * @param key
	 * @param value
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getStructure(@NotNull String table, String key, String value, int version) throws Exception  {
		//获�?�指定的JSON结构 <<<<<<<<<<<<<<
		SQLConfig config = createSQLConfig().setMethod(GET).setTable(table);
		config.setPrepared(false);
		config.setColumn(Arrays.asList("structure"));

		Map<String, Object> where = new HashMap<String, Object>();
		where.put("method", requestMethod.name());
		if (key != null) {
			where.put(key, value);
		}
		if (version > 0) {
			where.put(JSONRequest.KEY_VERSION + "{}", ">=" + version);
		}
		config.setWhere(where);
		config.setOrder(JSONRequest.KEY_VERSION + (version > 0 ? "+" : "-"));
		config.setCount(1);

		SQLExecutor executor = createSQLExecutor();

		//too many connections error: �?try-catch，�?�以让客户端看到是�?务器内部异常
		try {
			JSONObject result = executor.execute(config, false);
			return getJSONObject(result, "structure");//解决返回值套了一层 "structure":{}
		} finally {
			executor.close();
		}
	}



	//	protected SQLConfig itemConfig;
	/**获�?��?�个对象，该对象处于parentObject内
	 * @param parentPath parentObject的路径
	 * @param name parentObject的key
	 * @param request parentObject的value
	 * @param config for array item
	 * @return
	 * @throws Exception 
	 */
	@Override
	public JSONObject onObjectParse(final JSONObject request
			, String parentPath, String name, final SQLConfig arrayConfig, boolean isSubquery) throws Exception {

		if (Log.DEBUG) {
			Log.i(TAG, "\ngetObject:  parentPath = " + parentPath
					+ ";\n name = " + name + "; request = " + JSON.toJSONString(request));
		}
		if (request == null) {// Moment:{}   || request.isEmpty()) {//key-value�?�件
			return null;
		}

		int type = arrayConfig == null ? 0 : arrayConfig.getType();

		String[] arr = StringUtil.split(parentPath, "/");
		if (arrayConfig == null || arrayConfig.getPosition() == 0) {
			int d = arr == null ? 1 : arr.length + 1;
			if (queryDepth < d) {
				queryDepth = d;
				int maxQueryDepth = getMaxQueryDepth();
				if (queryDepth > maxQueryDepth) {
					throw new IllegalArgumentException(parentPath + "/" + name + ":{} 的深度(或者说层级) 为 " + queryDepth + " 已超�?，必须在 1-" + maxQueryDepth + " 内 !");
				}
			}
		}

		ObjectParser op = createObjectParser(request, parentPath, name, arrayConfig, isSubquery).parse();


		JSONObject response = null;
		if (op != null) {//TODO SQL查询结果为空时，functionMap和customMap还有没有�?义？
			if (arrayConfig == null) {//Common
				response = op.setSQLConfig().executeSQL().response();
			}
			else {//Array Item Child
				int query = arrayConfig.getQuery();

				//total 这里�?能用arrayConfig.getType()，因为在createObjectParser.onChildParse传到onObjectParse时已被改掉
				if (type == SQLConfig.TYPE_ITEM_CHILD_0 && query != JSONRequest.QUERY_TABLE
						&& arrayConfig.getPosition() == 0) {
					JSONObject rp = op.setMethod(RequestMethod.HEAD).setSQLConfig().executeSQL().getSqlReponse();
					if (rp != null) {
						int index = parentPath.lastIndexOf("]/");
						if (index >= 0) {
							int total = rp.getIntValue(JSONResponse.KEY_COUNT);
							putQueryResult(parentPath.substring(0, index) + "]/" + JSONResponse.KEY_TOTAL, total);

							if (total <= arrayConfig.getCount()*arrayConfig.getPage()) {
								query = JSONRequest.QUERY_TOTAL;//数�?�?够了，�?�?往�?�查询
							}
						}
					}

					op.setMethod(requestMethod);
				}

				//Table
				if (query == JSONRequest.QUERY_TOTAL) {
					response = null;//�?�?往�?�查询
				} else {
					response = op
							.setSQLConfig(arrayConfig.getCount(), arrayConfig.getPage(), arrayConfig.getPosition())
							.executeSQL()
							.response();
					//					itemConfig = op.getConfig();
				}
			}

			op.recycle();
			op = null;
		}

		return response;
	}

	/**获�?�对象数组，该对象数组处于parentObject内
	 * @param parentPath parentObject的路径
	 * @param name parentObject的key
	 * @param request parentObject的value
	 * @return 
	 * @throws Exception
	 */
	@Override
	public JSONArray onArrayParse(JSONObject request, String parentPath, String name, boolean isSubquery) throws Exception {
		if (Log.DEBUG) {
			Log.i(TAG, "\n\n\n onArrayParse parentPath = " + parentPath
					+ "; name = " + name + "; request = " + JSON.toJSONString(request));
		}

		//�?能�?许GETS，�?�则会被通过"[]":{"@role":"ADMIN"},"Table":{},"tag":"Table"绕过�?��?并能批�?查询
		if (RequestMethod.isGetMethod(requestMethod, false) == false) {
			throw new UnsupportedOperationException("key[]:{}�?�支�?GET方法�?�?�?许传 " + name + ":{} �?");
		}
		if (request == null || request.isEmpty()) {//jsonKey-jsonValue�?�件
			return null;
		}
		String path = getAbsPath(parentPath, name);


		//�?能改�?�，因为�?��?��?�能继续用到，导致1以上都改�?� []:{0:{Comment[]:{0:{Comment:{}},1:{...},...}},1:{...},...}
		final String query = request.getString(JSONRequest.KEY_QUERY);
		final Integer count = request.getInteger(JSONRequest.KEY_COUNT); //TODO 如果�?想用默认数�?�?�以改�? getIntValue(JSONRequest.KEY_COUNT);
		final int page = request.getIntValue(JSONRequest.KEY_PAGE);
		final Object join = request.get(JSONRequest.KEY_JOIN);

		int query2;
		if (query == null) {
			query2 = JSONRequest.QUERY_TABLE;
		}
		else {
			switch (query) {
			case "0":
			case JSONRequest.QUERY_TABLE_STRING:
				query2 = JSONRequest.QUERY_TABLE;
				break;
			case "1":
			case JSONRequest.QUERY_TOTAL_STRING:
				query2 = JSONRequest.QUERY_TOTAL;
				break;
			case "2":
			case JSONRequest.QUERY_ALL_STRING:
				query2 = JSONRequest.QUERY_ALL;
				break;
			default:
				throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_QUERY + ":value 中 value 的值�?�?�法�?必须在 [0,1,2] 或 [TABLE, TOTAL, ALL] 内 !");
			}
		}

		int maxPage = getMaxQueryPage();
		if (page < 0 || page > maxPage) {
			throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_PAGE + ":value 中 value 的值�?�?�法�?必须在 0-" + maxPage + " 内 !");
		}

		//�?用total�?制数�?了，�?�用中断机制，total�?�在query = 1,2的时候�?获�?�
		int count2 = isSubquery || count != null ? (count == null ? 0 : count) : getDefaultQueryCount();
		int max = isSubquery ? count2 : getMaxQueryCount();

		if (count2 < 0 || count2 > max) {
			throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_COUNT + ":value 中 value 的值�?�?�法�?必须在 0-" + max + " 内 !");
		}

		request.remove(JSONRequest.KEY_QUERY);
		request.remove(JSONRequest.KEY_COUNT);
		request.remove(JSONRequest.KEY_PAGE);
		request.remove(JSONRequest.KEY_JOIN);
		Log.d(TAG, "onArrayParse  query = " + query + "; count = " + count + "; page = " + page + "; join = " + join);

		if (request.isEmpty()) {//如果�?�件�?立，说明所有的 parentPath/name:request 中request都无效�?�?�?
			Log.e(TAG, "onArrayParse  request.isEmpty() >> return null;");
			return null;
		}


		int size = count2 == 0 ? max : count2;//count为�?页数�?，size为第page页实际数�?，max(size) = count
		Log.d(TAG, "onArrayParse  size = " + size + "; page = " + page);


		//key[]:{Table:{}}中key equals Table时 �??�?�Table
		int index = name == null ? -1 : name.lastIndexOf("[]");
		String childPath = index <= 0 ? null : Pair.parseEntry(name.substring(0, index), true).getKey(); // Table-key1-key2...

		//判断第一个key，�?�Table是�?�存在，如果存在就�??�?�
		String[] childKeys = StringUtil.split(childPath, "-", false);
		if (childKeys == null || childKeys.length <= 0 || request.containsKey(childKeys[0]) == false) {
			childKeys = null;
		}


		//Table<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		JSONArray response = new JSONArray();
		SQLConfig config = createSQLConfig()
				.setMethod(requestMethod)
				.setCount(size)
				.setPage(page)
				.setQuery(query2)
				.setJoinList(onJoinParse(join, request));

		JSONObject parent;
		//生�?size个
		for (int i = 0; i < (isSubquery ? 1 : size); i++) {
			parent = onObjectParse(request, path, "" + i, config.setType(SQLConfig.TYPE_ITEM).setPosition(i), isSubquery);
			if (parent == null || parent.isEmpty()) {
				break;
			}
			//key[]:{Table:{}}中key equals Table时 �??�?�Table
			response.add(getValue(parent, childKeys)); //null有�?义
		}
		//Table>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


		/*
		 * 支�?引用�?�值�?�的数组
			{
			    "User-id[]": {
			        "User": {
			            "contactIdList<>": 82002
			        }
			    },
			    "Moment-userId[]": {
			        "Moment": {
			            "userId{}@": "User-id[]"
			        }
			    }
			}
		 */
		Object fo = childKeys == null || response.isEmpty() ? null : response.get(0);
		if (fo instanceof Boolean || fo instanceof Number || fo instanceof String) { //[{}] 和 [[]] 都没�?义
			putQueryResult(path, response);
		}


		//�?��?�还�?�能用到，�?还原
		request.put(JSONRequest.KEY_QUERY, query);
		request.put(JSONRequest.KEY_COUNT, count);
		request.put(JSONRequest.KEY_PAGE, page);
		request.put(JSONRequest.KEY_JOIN, join);

		if (Log.DEBUG) {
			Log.i(TAG, "onArrayParse  return response = \n" + JSON.toJSONString(response) + "\n>>>>>>>>>>>>>>>\n\n\n");
		}
		return response;
	}

	/**多表�?�时筛选
	 * @param join "&/User/id@,</User[]/User/id{}@,</[]/Comment/momentId@"
	 * @param request
	 * @return 
	 * @throws Exception 
	 */
	private List<Join> onJoinParse(Object join, JSONObject request) throws Exception {
		JSONObject joinMap = null;

		if (join instanceof JSONObject) {
			joinMap = (JSONObject) join;
		}
		else if (join instanceof String) {
			String[] sArr = request == null || request.isEmpty() ? null : StringUtil.split((String) join);
			if (sArr != null && sArr.length > 0) {
				joinMap = new JSONObject();
				for (int i = 0; i < sArr.length; i++) {
					joinMap.put(sArr[i], new JSONObject());
				}
			}
		}
		else if (join != null){
			throw new UnsupportedDataTypeException(TAG + ".onJoinParse  join �?�能是 String 或 JSONObject 类型�?");
		}

		Set<Entry<String, Object>> set = joinMap == null ? null : joinMap.entrySet();
		if (set == null || set.isEmpty()) {
			Log.e(TAG, "doJoin  set == null || set.isEmpty() >> return null;");
			return null;
		}



		List<Join> joinList = new ArrayList<>();


		JSONObject tableObj;
		String targetPath;

		JSONObject targetObj;
		String targetTable;
		String targetKey;

		String path;

		//		List<String> onList = new ArrayList<>();
		for (Entry<String, Object> e : set) {//User/id@
			if (e.getValue() instanceof JSONObject == false) {
				throw new IllegalArgumentException(JSONRequest.KEY_JOIN + ":value 中value�?�?�法�?"
						+ "必须为 &/Table0/key0,</Table1/key1,... 或 { '&/Table0/key0':{}, '</Table1/key1':{},... } 这�?形�?�?");
			}

			//分割 /Table/key
			path = "" + e.getKey();

			int index = path.indexOf("/");
			if (index < 0) {
				throw new IllegalArgumentException(JSONRequest.KEY_JOIN + ":value 中value�?�?�法�?"
						+ "必须为 &/Table0/key0,</Table1/key1,... 或 { '&/Table0/key0':{}, '</Table1/key1':{},... } 这�?形�?�?");
			}
			String joinType = path.substring(0, index); //& | ! < > ( ) <> () *
			//			if (StringUtil.isEmpty(joinType, true)) {
			//				joinType = "|"; // FULL JOIN
			//			}
			path = path.substring(index + 1);

			index = path.indexOf("/");
			String tableKey = index < 0 ? null : path.substring(0, index); //User:owner
			String table = Pair.parseEntry(tableKey, true).getKey(); //User
			String key = StringUtil.isEmpty(table, true) ? null : path.substring(index + 1);//id@
			if (StringUtil.isEmpty(key, true)) {
				throw new IllegalArgumentException(JSONRequest.KEY_JOIN + ":value 中value�?�?�法�?"
						+ "必须为 &/Table0/key0,</Table1/key1,... 这�?形�?�?");
			}

			//�?�出Table对应的JSONObject，�?�内部引用赋值 key:value
			tableObj = request.getJSONObject(tableKey);
			targetPath = tableObj == null ? null : tableObj.getString(key);
			if (StringUtil.isEmpty(targetPath, true)) {
				throw new IllegalArgumentException("/" + path + ":value 中value必须为引用赋值的路径 '/targetTable/targetKey' �?");
			}

			//�?�出引用赋值路径targetPath对应的Table和key
			index = targetPath.lastIndexOf("/");
			targetKey = index < 0 ? null : targetPath.substring(index + 1);
			if (StringUtil.isEmpty(targetKey, true)) {
				throw new IllegalArgumentException("/" + path + ":'/targetTable/targetKey' 中targetKey�?能为空�?");
			}

			targetPath = targetPath.substring(0, index);
			index = targetPath.lastIndexOf("/");
			targetTable = index < 0 ? targetPath : targetPath.substring(index + 1);


			//对引用的JSONObject添加�?�件
			targetObj = request.getJSONObject(targetTable);
			if (targetObj == null) {
				throw new IllegalArgumentException(targetTable + "." + targetKey
						+ ":'/targetTable/targetKey' 中路径对应的对象�?存在�?");
			}

			tableObj.put(key, tableObj.remove(key)); //�?�?和SQLExcecutor缓存的Config里where顺�?一致，生�?的SQL也就一致

			Join j = new Join();
			j.setPath(path);
			j.setOriginKey(key);
			j.setOriginValue(targetPath);
			j.setJoinType(joinType);
			j.setName(table);
			j.setTargetName(targetTable);
			j.setTargetKey(targetKey);
			j.setKeyAndType(key);
			j.setTable(getJoinObject(table, tableObj, key));
			j.setOutter((JSONObject) e.getValue());

			joinList.add(j);

			//			onList.add(table + "." + key + " = " + targetTable + "." + targetKey); // ON User.id = Moment.userId

		}


		//拼接多个 SQLConfig 的SQL语�?�，然�?�执行，�?把结果分别缓存(Moment, User等)到 SQLExecutor 的 cacheMap
		//		AbstractSQLConfig config0 = null;
		//		String sql = "SELECT " + config0.getColumnString() + " FROM " + config0.getTable() + " INNER JOIN " + targetTable + " ON "
		//				+ onList.get(0) + config0.getGroupString() + config0.getHavingString() + config0.getOrderString();


		return joinList;
	}



	private static final List<String> JOIN_COPY_KEY_LIST;
	static {//TODO �?全
		JOIN_COPY_KEY_LIST = new ArrayList<String>();
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_DATABASE);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_SCHEMA);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_COLUMN);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_COMBINE);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_GROUP);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_HAVING);
		JOIN_COPY_KEY_LIST.add(JSONRequest.KEY_ORDER);
	}

	/**
	 * �?�指定json对象的id集�?�
	 * @param table
	 * @param key
	 * @param obj
	 * @param targetKey 
	 * @return null ? 全部 : 有�?的数组
	 */
	private JSONObject getJoinObject(String table, JSONObject obj, String key) {
		if (obj == null || obj.isEmpty()) {
			Log.e(TAG, "getIdList  obj == null || obj.isEmpty() >> return null;");
			return null;
		}
		if (StringUtil.isEmpty(key, true)) {
			Log.e(TAG, "getIdList  StringUtil.isEmpty(key, true) >> return null;");
			return null;
		}

		//�?�出所有join�?�件
		JSONObject requestObj = new JSONObject(true);//(JSONObject) obj.clone();//
		Set<String> set = new LinkedHashSet<>(obj.keySet());
		for (String k : set) {
			if (StringUtil.isEmpty(k, true)) {
				continue;
			}

			if (k.startsWith("@")) {
				if (JOIN_COPY_KEY_LIST.contains(k)) {
					requestObj.put(k, obj.get(k)); //�?留
				}
			}
			else {
				if (k.endsWith("@")) {
					if (k.equals(key)) {
						continue;
					}
					throw new UnsupportedOperationException(table + "." + k + " �?�?�法�?" + JSONRequest.KEY_JOIN
							+ " 关�?�的Table中�?�能有1个 key@:value �?");
				}

				if (k.contains("()") == false) { //�?需�?远程函数
					//					requestObj.put(k, obj.remove(k)); //remove是为了�?��?�?�?查询副表
					requestObj.put(k, obj.get(k)); //remove是为了�?��?�?�?查询副表
				}
			}
		}


		return requestObj;
	}

	@Override
	public int getDefaultQueryCount() {
		return DEFAULT_QUERY_COUNT;
	}
	@Override
	public int getMaxQueryPage() {
		return MAX_QUERY_PAGE;
	}
	@Override
	public int getMaxQueryCount() {
		return MAX_QUERY_COUNT;
	}
	@Override
	public int getMaxUpdateCount() {
		return MAX_UPDATE_COUNT;
	}
	@Override
	public int getMaxSQLCount() {
		return MAX_SQL_COUNT;
	}
	@Override
	public int getMaxObjectCount() {
		return MAX_OBJECT_COUNT;
	}
	@Override
	public int getMaxArrayCount() {
		return MAX_ARRAY_COUNT;
	}
	@Override
	public int getMaxQueryDepth() {
		return MAX_QUERY_DEPTH;
	}


	/**根�?�路径�?�值
	 * @param parent
	 * @param pathKeys
	 * @return
	 */
	protected static Object getValue(JSONObject parent, String[] pathKeys) {
		if (parent == null || pathKeys == null || pathKeys.length <= 0) {
			Log.w(TAG, "getChild  parent == null || pathKeys == null || pathKeys.length <= 0 >> return parent;");
			return parent;
		}

		//�?层到达child的直接容器JSONObject parent
		final int last = pathKeys.length - 1;
		for (int i = 0; i < last; i++) {//一步一步到达指定�?置
			if (parent == null) {//�?存在或路径错误(中间的key对应value�?是JSONObject)
				break;
			}
			parent = getJSONObject(parent, pathKeys[i]);
		}

		return parent == null ? null : parent.get(pathKeys[last]);
	}


	/**获�?�被�?赖引用的key的路径, 实时替�?�[] -> []/i
	 * @param parentPath
	 * @param valuePath
	 * @return
	 */
	public static String getValuePath(String parentPath, String valuePath) {
		if (valuePath.startsWith("/")) {
			valuePath = getAbsPath(parentPath, valuePath);
		} else {//处�?�[] -> []/i
			valuePath = replaceArrayChildPath(parentPath, valuePath);
		}
		return valuePath;
	}

	/**获�?��?对路径
	 * @param path
	 * @param name
	 * @return
	 */
	public static String getAbsPath(String path, String name) {
		Log.i(TAG, "getPath  path = " + path + "; name = " + name + " <<<<<<<<<<<<<");
		path = StringUtil.getString(path);
		name = StringUtil.getString(name);
		if (StringUtil.isNotEmpty(path, false)) {
			if (StringUtil.isNotEmpty(name, false)) {
				path += ((name.startsWith("/") ? "" : "/") + name);
			}
		} else {
			path = name;
		}
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		Log.i(TAG, "getPath  return " + path + " >>>>>>>>>>>>>>>>");
		return path;
	}

	/**替�?�[] -> []/i
	 * �?能写在getAbsPath里，因为name�?一定是�?赖路径
	 * @param parentPath
	 * @param valuePath
	 * @return
	 */
	public static String replaceArrayChildPath(String parentPath, String valuePath) {
		String[] ps = StringUtil.split(parentPath, "]/");//"[]/");
		if (ps != null && ps.length > 1) {
			String[] vs = StringUtil.split(valuePath, "]/");

			if (vs != null && vs.length > 0) {
				String pos;
				for (int i = 0; i < ps.length - 1; i++) {
					if (ps[i] == null || ps[i].equals(vs[i]) == false) {//�?许""？
						break;
					}

					pos = ps[i+1].contains("/") == false ? ps[i+1]
							: ps[i+1].substring(0, ps[i+1].indexOf("/"));
					if (
							//StringUtil.isNumer(pos) && 
							vs[i+1].startsWith(pos + "/") == false) {
						vs[i+1] = pos + "/" + vs[i+1];
					}
				}
				return StringUtil.getString(vs, "]/");
			}
		}
		return valuePath;
	}

	//�?赖引用关系 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**将已获�?�完�?的object的内容替�?�requestObject里对应的值
	 * @param path object的路径
	 * @param result 需�?被关�?�的object
	 */
	@Override
	public synchronized void putQueryResult(String path, Object result) {
		Log.i(TAG, "\n putQueryResult  valuePath = " + path + "; result = " + result + "\n <<<<<<<<<<<<<<<<<<<<<<<");
		//		if (queryResultMap.containsKey(valuePath)) {//�?��?存被关�?�的value
		Log.d(TAG, "putQueryResult  queryResultMap.containsKey(valuePath) >> queryResultMap.put(path, result);");
		queryResultMap.put(path, result);
		//		}
	}
	/**根�?�路径获�?�值
	 * @param valuePath
	 * @return parent == null ? valuePath : parent.get(keys[keys.length - 1])
	 */
	@Override
	public Object getValueByPath(String valuePath) {
		Log.i(TAG, "<<<<<<<<<<<<<<< \n getValueByPath  valuePath = " + valuePath + "\n <<<<<<<<<<<<<<<<<<");
		if (StringUtil.isEmpty(valuePath, true)) {
			Log.e(TAG, "getValueByPath  StringUtil.isNotEmpty(valuePath, true) == false >> return null;");
			return null;
		}
		Object target = queryResultMap.get(valuePath);
		if (target != null) {
			return target;
		}

		//�?�出key被valuePath包�?�的result，�?从里�?�获�?�key对应的value
		Set<String> set = queryResultMap.keySet();
		JSONObject parent = null;
		String[] keys = null;
		for (String path : set) {
			if (valuePath.startsWith(path + "/")) {
				try {
					parent = (JSONObject) queryResultMap.get(path);
				} catch (Exception e) {
					Log.e(TAG, "getValueByPath  try { parent = (JSONObject) queryResultMap.get(path); } catch { "
							+ "\n parent not instanceof JSONObject!");
					parent = null;
				}
				if (parent != null) {
					keys = StringUtil.splitPath(valuePath.substring(path.length()));
				}
				break;
			}
		}

		//�?层到达targetKey的直接容器JSONObject parent
		if (keys != null && keys.length > 1) {
			for (int i = 0; i < keys.length - 1; i++) {//一步一步到达指定�?置parentPath
				if (parent == null) {//�?存在或路径错误(中间的key对应value�?是JSONObject)
					break;
				}
				parent = getJSONObject(parent, keys[i]);
			}
		}

		if (parent != null) {
			Log.i(TAG, "getValueByPath >> get from queryResultMap >> return  parent.get(keys[keys.length - 1]);");
			target = parent.get(keys[keys.length - 1]); //值为null应该报错NotExistExeption，一般都是id关�?�，�?�?�为null，�?�则�?�能绕过安全机制
			if (target != null) {
				Log.i(TAG, "getValueByPath >> getValue >> return target = " + target);
				return target;
			}
		}


		//从requestObject中�?�值
		target = getValue(requestObject, StringUtil.splitPath(valuePath));
		if (target != null) {
			Log.i(TAG, "getValueByPath >> getValue >> return target = " + target);
			return target;
		}

		Log.i(TAG, "getValueByPath  return valuePath;");
		return valuePath;
	}

	//�?赖引用关系 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




	public static JSONObject getJSONObject(JSONObject object, String key) {
		try {
			return object.getJSONObject(key);
		} catch (Exception e) {
			Log.i(TAG, "getJSONObject  try { return object.getJSONObject(key);"
					+ " } catch (Exception e) { \n"  + e.getMessage());
		}
		return null;
	}


	public static final String KEY_CONFIG = "config";

	/**执行 SQL 并返回 JSONObject
	 * @param config
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject executeSQL(SQLConfig config, boolean isSubquery) throws Exception {
		if (config == null) {
			Log.d(TAG, "executeSQL  config == null >> return null;");
			return null;
		}

		if (isSubquery) {
			JSONObject sqlObj = new JSONObject(true);
			sqlObj.put(KEY_CONFIG, config);
			return sqlObj;//容易丢失信�?� JSON.parseObject(config);
		}

		try {
			boolean explain = config.isExplain();
			JSONObject result;
			if (explain) { //如果先执行 explain，则 execute 会死循环，所以�?�能先执行�?� explain
				config.setExplain(false); //对下�?� config.getSQL(false); 生效
				JSONObject res = sqlExecutor.execute(config, false);

				config.setExplain(explain);
				JSONObject explainResult = config.isMain() && config.getPosition() != 0 ? null : sqlExecutor.execute(config, false);

				if (explainResult == null) {
					result = res;
				}
				else {
					result = new JSONObject(true);
					result.put(KEY_EXPLAIN, explainResult);
					result.putAll(res);
				}
			}
			else {
				result = sqlExecutor.execute(config, false);
			}

			return parseCorrectResponse(config.getTable(), result);
		}
		catch (Exception e) {
			if (Log.DEBUG == false && e instanceof SQLException) {
				throw new SQLException("数�?�库驱动执行异常SQLException，�?� Log.DEBUG 模�?下�?显示详情，�?��?泄�?真实模�?�??�?表�??等�?�?信�?�", e);
			}
			throw e;
		}
		finally {
			if (config.getPosition() == 0) {
				int maxSQLCount = getMaxSQLCount();
				int sqlCount = sqlExecutor.getExecutedSQLCount();
				Log.d(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n\n\n 已执行 " + sqlCount + "/" + maxSQLCount + " �?� SQL \n\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				if (sqlCount > maxSQLCount) {
					throw new IllegalArgumentException("截至 " + config.getTable() + " 已执行 " + sqlCount + " �?� SQL，数�?已超�?，必须在 0-" + maxSQLCount + " 内 !");
				}
			}
		}
	}



}
