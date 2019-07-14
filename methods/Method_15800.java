@Override public ErrorType judge(OAuth2Response response){
  if (response.status() != 500) {
    return null;
  }
  String result=response.asString();
  if (result == null) {
    return ErrorType.OTHER;
  }
  if (!result.trim().startsWith("{")) {
    return null;
  }
  try {
    JSONObject jsonRes=JSON.parseObject(result);
    if (jsonRes.size() > 5)     return null;
    Integer status=jsonRes.getInteger("status");
    if (status == null && response.status() == 200) {
      return null;
    }
    if (status != null) {
      if (status == 200) {
        return null;
      }
      return ErrorType.fromCode(status).orElse(ErrorType.OTHER);
    }
    if (jsonRes.get("message") != null) {
      return ErrorType.valueOf(jsonRes.getString("message"));
    }
  }
 catch (  Exception ignore) {
  }
  return null;
}
