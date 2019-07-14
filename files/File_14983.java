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


package apijson.demo.client.manager;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;

import zuo.biao.apijson.StringUtil;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.util.Log;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import apijson.demo.client.application.APIJSONApplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**HTTP请求管�?�类
 * @author Lemon
 * @use HttpManager.getInstance().get(...)或HttpManager.getInstance().post(...)  > 在回调方法onHttpRequestSuccess和onHttpRequestError处�?�HTTP请求结果
 * @must 解决getToken，getResponseCode，getResponseData中的TODO
 */
public class HttpManager {
	private static final String TAG = "HttpManager";


	private Context context;
	private static HttpManager instance;// �?�例
	private HttpManager(Context context) {
		this.context = context;

	}

	public static HttpManager getInstance() {
		if (instance == null) {
			synchronized (HttpManager.class) {
				if (instance == null) {
					instance = new HttpManager(APIJSONApplication.getInstance());
				}
			}
		}
		return instance;
	}



	/**
	 * 列表首页页�?。有些�?务器设置为1，�?�列表页�?从1开始
	 */
	public static final int PAGE_NUM_0 = 0;

	public static final String KEY_TOKEN = "token";
	public static final String KEY_COOKIE = "cookie";


	public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	/**POST请求
	 * @param url_ 接�?�url
	 * @param request 请求
	 * @param requestCode
	 *            请求�?，类似onActivityResult中请求�?，当�?�一activity中以实现接�?�方�?�?�起多个网络请求时，请求结�?��?�都会回调
	 *            {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br/>
	 *            在�?�起请求的类中�?�以用requestCode�?�区分�?�个请求
	 * @param listener
	 */
	public void post(final String url_, final com.alibaba.fastjson.JSONObject request
			, final int requestCode, final OnHttpResponseListener listener) {
		new AsyncTask<Void, Void, Exception>() {

			String result;
			@Override
			protected Exception doInBackground(Void... params) {

				try {
					String url = StringUtil.getNoBlankString(url_);

					OkHttpClient client = getHttpClient(url);
					if (client == null) {
						return new Exception(TAG + ".post  AsyncTask.doInBackground  client == null >> return;");
					}
					String body = JSON.toJSONString(request);
					Log.d(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n post  url = " + url_ + "\n request = \n" + body);
					
					RequestBody requestBody = RequestBody.create(TYPE_JSON, body);

					result = getResponseJson(client, new Request.Builder()
							.addHeader(KEY_TOKEN, getToken(url)).url(StringUtil.getNoBlankString(url))
							.post(requestBody).build());
					Log.d(TAG, "\n post  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
				} catch (Exception e) {
					Log.e(TAG, "post  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
							"} catch (Exception e) {\n" + e.getMessage());
					return e;
				}

				return null;
			}

			@Override
			protected void onPostExecute(Exception exception) {
				super.onPostExecute(exception);
				listener.onHttpResponse(requestCode, result, exception);
			}

		}.execute();
	}


	//httpGet/httpPost 内调用方法 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**
	 * @param url
	 * @return
	 */
	private OkHttpClient getHttpClient(String url) {
		Log.i(TAG, "getHttpClient  url = " + url);
		if (StringUtil.isNotEmpty(url, true) == false) {
			Log.e(TAG, "getHttpClient  StringUtil.isNotEmpty(url, true) == false >> return null;");
			return null;
		}

		OkHttpClient client = new OkHttpClient();
		client.setCookieHandler(new HttpHead());
		client.setConnectTimeout(15, TimeUnit.SECONDS);
		client.setWriteTimeout(10, TimeUnit.SECONDS);
		client.setReadTimeout(10, TimeUnit.SECONDS);

		return client;
	}

	/**
	 * @param tag
	 * @must demo_***改为�?务器设定值
	 * @return
	 */
	public String getToken(String tag) {
		return context.getSharedPreferences(KEY_TOKEN, Context.MODE_PRIVATE).getString(KEY_TOKEN + tag, "");
	}
	/**
	 * @param tag
	 * @param value
	 */
	public void saveToken(String tag, String value) {
		context.getSharedPreferences(KEY_TOKEN, Context.MODE_PRIVATE)
				.edit()
				.remove(KEY_TOKEN + tag)
				.putString(KEY_TOKEN + tag, value)
				.commit();
	}


	/**
	 * @return
	 */
	public String getCookie() {
		return context.getSharedPreferences(KEY_COOKIE, Context.MODE_PRIVATE).getString(KEY_COOKIE, "");
	}
	/**
	 * @param value
	 */
	public void saveCookie(String value) {
		context.getSharedPreferences(KEY_COOKIE, Context.MODE_PRIVATE)
				.edit()
				.remove(KEY_COOKIE)
				.putString(KEY_COOKIE, value)
				.commit();
	}


	/**
	 * @param client
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String getResponseJson(OkHttpClient client, Request request) throws Exception {
		if (client == null || request == null) {
			Log.e(TAG, "getResponseJson  client == null || request == null >> return null;");
			return null;
		}
		Response response = client.newCall(request).execute();
		return response.isSuccessful() ? response.body().string() : null;
	}

	/**从object中获�?�key对应的值
	 * *获�?�如果T是基本类型容易崩溃，所以需�?try-catch
	 * @param json
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public <T> T getValue(String json, String key) throws JSONException {
		return getValue(JSON.parseObject(json), key);
	}
	/**从object中获�?�key对应的值
	 * *获�?�如果T是基本类型容易崩溃，所以需�?try-catch
	 * @param object
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue(JSONObject object, String key) throws JSONException {
		return (T) object.get(key);
	}

	//httpGet/httpPost 内调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




	/**http请求头
	 */
	public class HttpHead extends CookieHandler {
		public HttpHead() {
		}

		@Override
		public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
			String cookie = getCookie();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			map.putAll(requestHeaders);
			if (!TextUtils.isEmpty(cookie)) {
				List<String> cList = new ArrayList<String>();
				cList.add(cookie);
				map.put("Cookie", cList);
			}
			return map;
		}

		@Override
		public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
			List<String> list = responseHeaders.get("Set-Cookie");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					String cookie = list.get(i);
					if (cookie.startsWith("JSESSIONID")) {
						saveCookie(list.get(i));
						break;
					}
				}
			}
		}

	}


}
