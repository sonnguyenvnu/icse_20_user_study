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

package apijson.demo.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import apijson.demo.server.model.BaseModel;
import apijson.demo.server.model.Comment;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.Log;
import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.RequestRole;
import zuo.biao.apijson.StringUtil;
import zuo.biao.apijson.server.Function;
import zuo.biao.apijson.server.JSONRequest;
import zuo.biao.apijson.server.NotNull;


/**å?¯è¿œç¨‹è°ƒç”¨çš„å‡½æ•°ç±»
 * @author Lemon
 */
public class DemoFunction extends Function implements FunctionList {
	private static final String TAG = "DemoFunction";

	private final HttpSession session;
	public DemoFunction(HttpSession session) {
		this.session = session;
	}

	public static void test() throws Exception {
		int i0 = 1, i1 = -2;
		JSONObject request = new JSONObject(); 
		request.put("id", 10);
		request.put("i0", i0);
		request.put("i1", i1);
		JSONArray arr = new JSONArray();
		arr.add(new JSONObject());
		request.put("arr", arr);

		JSONArray array = new JSONArray();
		array.add(1);//new JSONObject());
		array.add(2);//new JSONObject());
		array.add(4);//new JSONObject());
		array.add(10);//new JSONObject());
		request.put("array", array);

		request.put("position", 1);
		request.put("@position", 0);

		request.put("key", "key");
		JSONObject object = new JSONObject();
		object.put("key", true);
		request.put("object", object);


		Log.i(TAG, "plus(1,-2) = " + new DemoFunction(null).invoke(request, "plus(i0,i1)"));
		Log.i(TAG, "count([1,2,4,10]) = " + new DemoFunction(null).invoke(request, "countArray(array)"));
		Log.i(TAG, "isContain([1,2,4,10], 10) = " + new DemoFunction(null).invoke(request, "isContain(array,id)"));
		Log.i(TAG, "getFromArray([1,2,4,10], 0) = " + new DemoFunction(null).invoke(request, "getFromArray(array,@position)"));
		Log.i(TAG, "getFromObject({key:true}, key) = " + new DemoFunction(null).invoke(request, "getFromObject(object,key)"));

	}




	/**å??å°„è°ƒç”¨
	 * @param request
	 * @param function ä¾‹å¦‚get(object,key)ï¼Œå?‚æ•°å?ªå…?è®¸å¼•ç”¨ï¼Œä¸?èƒ½ç›´æŽ¥ä¼ å€¼
	 * @return
	 */
	public Object invoke(JSONObject request, String function) throws Exception {
		//TODO  ä¸?å…?è®¸è°ƒç”¨invokeï¼Œé?¿å…?æ­»å¾ªçŽ¯
		//		if (function.startsWith("invoke(")) {
		//			
		//		}
		return invoke(this, request, function);
	}



	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object verifyIdList(@NotNull JSONObject request, @NotNull String idList) throws Exception {
		Object obj = request.get(idList);
		if (obj instanceof Collection == false) {
			throw new IllegalArgumentException(idList + " ä¸?ç¬¦å?ˆ Array ç±»åž‹! ç»“æž„å¿…é¡»æ˜¯ [] ï¼?");
		}
		JSONArray array = (JSONArray) obj;
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof Long == false && array.get(i) instanceof Integer == false) {
					throw new IllegalArgumentException(idList + " å†…å­—ç¬¦ " + array.getString(i) + " ä¸?ç¬¦å?ˆ Long ç±»åž‹!");
				}
			}
		}
		return null;
	}


	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object verifyURLList(@NotNull JSONObject request, @NotNull String urlList) throws Exception {
		Object obj = request.get(urlList);
		if (obj instanceof Collection == false) {
			throw new IllegalArgumentException(urlList + " ä¸?ç¬¦å?ˆ Array ç±»åž‹! ç»“æž„å¿…é¡»æ˜¯ [] ï¼?");
		}
		JSONArray array = (JSONArray) obj;
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				if (StringUtil.isUrl(array.getString(i)) == false) {
					throw new IllegalArgumentException(urlList + " å†…å­—ç¬¦ " + array.getString(i) + " ä¸?ç¬¦å?ˆ URL æ ¼å¼?!");
				}
			}
		}
		return null;
	}

	/**åˆ¤æ–­arrayæ˜¯å?¦ä¸ºç©º
	 * @param request
	 * @param array
	 * @return
	 */
	public int deleteChildComment(@NotNull JSONObject rq, @NotNull String toId) throws Exception {
		long tid = rq.getLongValue(toId);
		if (tid <= 0 || rq.getIntValue(JSONResponse.KEY_COUNT) <= 0) {
			return 0;
		}

		//é€’å½’èŽ·å?–åˆ°å…¨éƒ¨å­?è¯„è®ºid
		
		JSONRequest request = new JSONRequest();
		
		//Comment<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		JSONRequest comment = new JSONRequest();
		comment.put("id{}", getChildCommentIdList(tid));
		request.put("Comment", comment);
		
		//Comment>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JSONObject rp = new DemoParser(RequestMethod.DELETE).setNoVerify(true).parseResponse(request);
		
		JSONObject c = rp.getJSONObject("Comment");
		return c == null ? 0 : c.getIntValue(JSONResponse.KEY_COUNT);
	}


	private JSONArray getChildCommentIdList(long tid) {
		
		JSONArray arr = new JSONArray();
		
		JSONRequest request = new JSONRequest();
		
		//Comment-id[]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		JSONRequest idItem = new JSONRequest();
		
		//Comment<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		JSONRequest comment = new JSONRequest();
		comment.put("toId", tid);
		comment.setColumn("id");
		idItem.put("Comment", comment);
		//Comment>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		request.putAll(idItem.toArray(0, 0, "Comment-id"));
		//Comment-id[]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JSONObject rp = new DemoParser().setNoVerify(true).parseResponse(request);

		JSONArray a = rp.getJSONArray("Comment-id[]");
		if (a != null) {
			arr.addAll(a);
			
			JSONArray a2;
			for (int i = 0; i < a.size(); i++) {
				
				a2 = getChildCommentIdList(a.getLongValue(i));
				if (a2 != null) {
					arr.addAll(a2);
				}
			}
		}
		
		return arr;
	}
	

	/**TODO ä»…ç”¨æ?¥æµ‹è¯• "key-()":"getIdList()" å’Œ "key()":"getIdList()"
	 * @param request
	 * @return JSONArray å?ªèƒ½ç”¨JSONArrayï¼Œç”¨long[]ä¼šåœ¨SQLConfigè§£æž?å´©æºƒ
	 * @throws Exception
	 */
	public JSONArray getIdList(@NotNull JSONObject request) throws Exception {
		return new JSONArray(new ArrayList<Object>(Arrays.asList(12, 15, 301, 82001, 82002, 38710)));
	}


	/**TODO ä»…ç”¨æ?¥æµ‹è¯• "key-()":"verifyAccess()"
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object verifyAccess(@NotNull JSONObject request) throws Exception {
		long userId = request.getLongValue(zuo.biao.apijson.JSONObject.KEY_USER_ID);
		RequestRole role = RequestRole.get(request.getString(zuo.biao.apijson.JSONObject.KEY_ROLE));
		if (role == RequestRole.OWNER && userId != DemoVerifier.getVisitorId(session)) {
			throw new IllegalAccessException("ç™»å½•ç”¨æˆ·ä¸Žè§’è‰²OWNERä¸?åŒ¹é…?ï¼?");
		}
		return null;
	}





	public double plus(@NotNull JSONObject request, String i0, String i1) {
		return request.getDoubleValue(i0) + request.getDoubleValue(i1);
	}
	public double minus(@NotNull JSONObject request, String i0, String i1) {
		return request.getDoubleValue(i0) - request.getDoubleValue(i1);
	}
	public double multiply(@NotNull JSONObject request, String i0, String i1) {
		return request.getDoubleValue(i0) * request.getDoubleValue(i1);
	}
	public double divide(@NotNull JSONObject request, String i0, String i1) {
		return request.getDoubleValue(i0) / request.getDoubleValue(i1);
	}

	//åˆ¤æ–­æ˜¯å?¦ä¸ºç©º <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**åˆ¤æ–­arrayæ˜¯å?¦ä¸ºç©º
	 * @param request
	 * @param array
	 * @return
	 */
	@Override
	public boolean isArrayEmpty(@NotNull JSONObject request, String array) {
		return BaseModel.isEmpty(request.getJSONArray(array));
	}
	/**åˆ¤æ–­objectæ˜¯å?¦ä¸ºç©º
	 * @param request
	 * @param object
	 * @return
	 */
	@Override
	public boolean isObjectEmpty(@NotNull JSONObject request, String object) {
		return BaseModel.isEmpty(request.getJSONObject(object)); 
	}
	//åˆ¤æ–­æ˜¯å?¦ä¸ºç©º >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//åˆ¤æ–­æ˜¯å?¦ä¸ºåŒ…å?« <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**åˆ¤æ–­arrayæ˜¯å?¦åŒ…å?«value
	 * @param request
	 * @param array
	 * @param value
	 * @return
	 */
	@Override
	public boolean isContain(@NotNull JSONObject request, String array, String value) {
		//è§£å†³isContain((List<Long>) [82001,...], (Integer) 82001) == falseå?Šç±»ä¼¼é—®é¢˜, listå…ƒç´ å?¯èƒ½æ˜¯ä»Žæ•°æ?®åº“æŸ¥åˆ°çš„bigintç±»åž‹çš„å€¼
		//		return BaseModel.isContain(request.getJSONArray(array), request.get(value));

		//ä¸?ç”¨å‡†ç¡®çš„çš„ request.getString(value).getClass() ï¼Œå› ä¸ºLongå€¼è½¬Integerå´©æºƒï¼Œè€Œä¸”è½¬æˆ?ä¸€ç§?ç±»åž‹æœ¬èº«å°±å’Œå­—ç¬¦ä¸²å¯¹æ¯”æ•ˆæžœä¸€æ ·äº†ã€‚
		List<String> list = com.alibaba.fastjson.JSON.parseArray(request.getString(array), String.class);
		return list != null && list.contains(request.getString(value));
	}
	/**åˆ¤æ–­objectæ˜¯å?¦åŒ…å?«key
	 * @param request
	 * @param object
	 * @param key
	 * @return
	 */
	@Override
	public boolean isContainKey(@NotNull JSONObject request, String object, String key) { 
		return BaseModel.isContainKey(request.getJSONObject(object), request.getString(key)); 
	}
	/**åˆ¤æ–­objectæ˜¯å?¦åŒ…å?«value
	 * @param request
	 * @param object
	 * @param value
	 * @return
	 */
	@Override
	public boolean isContainValue(@NotNull JSONObject request, String object, String value) { 
		return BaseModel.isContainValue(request.getJSONObject(object), request.get(value));
	}
	//åˆ¤æ–­æ˜¯å?¦ä¸ºåŒ…å?« >>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//èŽ·å?–é›†å?ˆé•¿åº¦ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–æ•°é‡?
	 * @param request
	 * @param array
	 * @return
	 */
	@Override
	public int countArray(@NotNull JSONObject request, String array) { 
		return BaseModel.count(request.getJSONArray(array)); 
	}
	/**èŽ·å?–æ•°é‡?
	 * @param request
	 * @param object
	 * @return
	 */
	@Override
	public int countObject(@NotNull JSONObject request, String object) {
		return BaseModel.count(request.getJSONObject(object)); 
	}
	//èŽ·å?–é›†å?ˆé•¿åº¦ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//æ ¹æ?®é”®èŽ·å?–å€¼ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–
	 ** @param request
	 * @param array
	 * @param position
	 * @return
	 */
	@Override
	public Object getFromArray(@NotNull JSONObject request, String array, String position) { 
		return BaseModel.get(request.getJSONArray(array), request.getIntValue(position)); 
	}
	/**èŽ·å?–
	 * @param request
	 * @param object
	 * @param key
	 * @return
	 */
	@Override
	public Object getFromObject(@NotNull JSONObject request, String object, String key) { 
		return BaseModel.get(request.getJSONObject(object), request.getString(key));
	}
	//æ ¹æ?®é”®èŽ·å?–å€¼ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//èŽ·å?–é?žåŸºæœ¬ç±»åž‹å¯¹åº”åŸºæœ¬ç±»åž‹çš„é?žç©ºå€¼ <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**èŽ·å?–é?žç©ºå€¼
	 * @param request
	 * @param value
	 * @return
	 */
	@Override
	public boolean booleanValue(@NotNull JSONObject request, String value) { 
		return request.getBooleanValue(value);
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param request
	 * @param value
	 * @return
	 */
	@Override
	public int intValue(@NotNull JSONObject request, String value) {  
		return request.getIntValue(value);
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param request
	 * @param value
	 * @return
	 */
	@Override
	public long longValue(@NotNull JSONObject request, String value) {   
		return request.getLongValue(value);
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param request
	 * @param value
	 * @return
	 */
	@Override
	public float floatValue(@NotNull JSONObject request, String value) {  
		return request.getFloatValue(value);
	}
	/**èŽ·å?–é?žç©ºå€¼
	 * @param request
	 * @param value
	 * @return
	 */
	@Override
	public double doubleValue(@NotNull JSONObject request, String value) {    
		return request.getDoubleValue(value); 
	}
	//èŽ·å?–é?žåŸºæœ¬ç±»åž‹å¯¹åº”åŸºæœ¬ç±»åž‹çš„é?žç©ºå€¼ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>




}
