@Override public Token create() throws WeixinException {
  WeixinResponse response=weixinExecutor.get(String.format(URLConsts.JS_TICKET_URL,weixinTokenManager.getAccessToken(),ticketType.name()));
  JSONObject result=response.getAsJson();
  return new Token(result.getString("ticket"),result.getLongValue("expires_in") * 1000l);
}
