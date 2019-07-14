/** 
 * POST??
 * @param url_ ??url
 * @param request ??
 * @param requestCode ??????onActivityResult????????activity??????????????????????????? {@link OnHttpResponseListener#onHttpResponse(int,String,Exception)}<br/> ???????????requestCode???????
 * @param listener
 */
public void post(final String url_,final com.alibaba.fastjson.JSONObject request,final int requestCode,final OnHttpResponseListener listener){
  new AsyncTask<Void,Void,Exception>(){
    @Override protected Exception doInBackground(    Void... params){
      try {
        String url=StringUtil.getNoBlankString(url_);
        OkHttpClient client=getHttpClient(url);
        if (client == null) {
          return new Exception(TAG + ".post  AsyncTask.doInBackground  client == null >> return;");
        }
        String body=JSON.toJSONString(request);
        Log.d(TAG,"\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n post  url = " + url_ + "\n request = \n" + body);
        RequestBody requestBody=RequestBody.create(TYPE_JSON,body);
        result=getResponseJson(client,new Request.Builder().addHeader(KEY_TOKEN,getToken(url)).url(StringUtil.getNoBlankString(url)).post(requestBody).build());
        Log.d(TAG,"\n post  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
      }
 catch (      Exception e) {
        Log.e(TAG,"post  AsyncTask.doInBackground  try {  result = getResponseJson(..." + "} catch (Exception e) {\n" + e.getMessage());
        return e;
      }
      return null;
    }
    @Override protected void onPostExecute(    Exception exception){
      super.onPostExecute(exception);
      listener.onHttpResponse(requestCode,result,exception);
    }
  }
.execute();
}
