private void decode(String str){
  if (str != null && str.startsWith("{")) {
    try {
      JSONObject json=new JSONObject(str);
      if (!json.isNull("errors")) {
        JSONObject error=json.getJSONArray("errors").getJSONObject(0);
        this.errorMessage=error.getString("message");
        this.errorCode=getInt("code",error);
      }
    }
 catch (    JSONException ignore) {
    }
  }
}
