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

package apijson.demo.server;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.server.AbstractParser;
import zuo.biao.apijson.server.JSONRequest;
import zuo.biao.apijson.server.SQLConfig;


/**请求解�?器
 * @author Lemon
 */
public class DemoParser extends AbstractParser<Long> {


	public DemoParser() {
		super();
	}
	public DemoParser(RequestMethod method) {
		super(method);
	}
	public DemoParser(RequestMethod method, boolean noVerify) {
		super(method, noVerify);
	}

	protected HttpSession session;
	public HttpSession getSession() {
		return session;
	}
	public DemoParser setSession(HttpSession session) {
		this.session = session;
		setVisitor(DemoVerifier.getVisitor(session));
		return this;
	}


	@Override
	public DemoVerifier createVerifier() {
		return new DemoVerifier();
	}
	@Override
	public DemoSQLConfig createSQLConfig() {
		return new DemoSQLConfig();
	}
	@Override
	public DemoSQLExecutor createSQLExecutor() {
		return new DemoSQLExecutor();
	}

	@Override
	public JSONObject parseResponse(JSONObject request) {
		//补充format
		if (session != null && request != null && request.get(JSONRequest.KEY_FORMAT) == null) {
			request.put(JSONRequest.KEY_FORMAT, session.getAttribute(JSONRequest.KEY_FORMAT));
		}
		return super.parseResponse(request);
	}

	private DemoFunction function;
	@Override
	public Object onFunctionParse(JSONObject json, String fun) throws Exception {
		if (function == null) {
			function = new DemoFunction(requestMethod, session);
		}
		return function.invoke(fun, json);
	}


	@Override
	public DemoObjectParser createObjectParser(JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception {

		return new DemoObjectParser(session, request, parentPath, name, arrayConfig, isSubquery) {

			//TODO 删除，onPUTArrayParse改用MySQL函数JSON_ADD, JSON_REMOVE等
			@Override
			public JSONObject parseResponse(JSONRequest request) throws Exception {
				DemoParser demoParser = new DemoParser(RequestMethod.GET);
				demoParser.setSession(session);
				//						parser.setNoVerifyRequest(noVerifyRequest)
				demoParser.setNoVerifyLogin(noVerifyLogin);
				demoParser.setNoVerifyRole(noVerifyRole);
				return demoParser.parseResponse(request);
			}


			//			@Override
			//			protected DemoSQLConfig newQueryConfig() {
			//				if (itemConfig != null) {
			//					return itemConfig;
			//				}
			//				return super.newQueryConfig();
			//			}

			//导致最多评论的(Strong 30个)的那个动�?详情界�?�Android(82001)无姓�??和头�?，�?�User=null
			//			@Override
			//			protected void onComplete() {
			//				if (response != null) {
			//					putQueryResult(path, response);//解决获�?�关�?�数�?�时requestObject里�?存在需�?的关�?�数�?�
			//				}
			//			}

		}.setMethod(requestMethod).setParser(this);
	}



	@Override
	public void onVerifyContent() throws Exception {
		//补充全局缺�?版本�?�  //�?�能在默认为1的�?�??下这个请求version就需�?为0  requestObject.getIntValue(JSONRequest.KEY_VERSION) <= 0) {
		if (session != null && requestObject.get(JSONRequest.KEY_VERSION) == null) {
			requestObject.put(JSONRequest.KEY_VERSION, session.getAttribute(JSONRequest.KEY_VERSION));
		}
		super.onVerifyContent();
	}

	//	//�?��?写�?�设置最大查询数�?
	//	@Override
	//	public int getMaxQueryCount() {
	//		return 50;
	//	}

}
