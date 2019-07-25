private void add(String query,int rating){
  JSONObject jsonBody=new JSONObject();
  try {
    jsonBody.put("value",rating);
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  final String mRequestBody=jsonBody.toString();
  JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(query,null,new Response.Listener<JSONObject>(){
    @Override public void onResponse(    JSONObject response){
      parseMarkedResponse(response);
    }
  }
,new Response.ErrorListener(){
    @Override public void onErrorResponse(    VolleyError error){
      Log.e("webi","Volley Error: " + error.getCause());
    }
  }
){
    @Override public byte[] getBody(){
      try {
        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
      }
 catch (      UnsupportedEncodingException uee) {
        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",mRequestBody,"utf-8");
        return null;
      }
    }
    @Override public Map<String,String> getHeaders() throws AuthFailureError {
      Map<String,String> headers=new HashMap<String,String>();
      headers.put("content-type","application/json;charset=utf-8");
      return headers;
    }
  }
;
  tmdbrequestQueue.add(jsonObjectRequest);
}
