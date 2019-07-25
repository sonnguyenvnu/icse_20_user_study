private ApiResult excute(String uri,User user,InputStream avatar) throws WeixinException {
  JSONObject obj=(JSONObject)JSON.toJSON(user);
  Object val=obj.remove("extattr");
  if (val != null) {
    JSONObject attrs=new JSONObject();
    attrs.put("attrs",val);
    obj.put("extattr",attrs);
  }
  val=obj.remove("status");
  if (val != null) {
    obj.put("enable",val);
  }
  if (avatar != null) {
    obj.put("avatar_mediaid",mediaApi.uploadMedia(0,avatar,null));
  }
 else {
    obj.put("avatar_mediaid",obj.remove("avatar"));
  }
  Token token=tokenManager.getCache();
  WeixinResponse response=weixinExecutor.post(String.format(uri,token.getAccessToken()),obj.toJSONString());
  return response.getAsResult();
}
