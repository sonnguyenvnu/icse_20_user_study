@Override public Token create() throws WeixinException {
  String tokenUrl=String.format(URLConsts.ASSESS_TOKEN_URL,corpid,corpsecret);
  WeixinResponse response=weixinExecutor.get(tokenUrl);
  JSONObject result=response.getAsJson();
  return new Token(result.getString("access_token"),result.getLongValue("expires_in") * 1000l);
}
