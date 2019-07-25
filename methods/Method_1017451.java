/** 
 * ???????????(??????userid?????????)
 * @param userid ??ID
 * @return ????
 * @see <a href= "https://work.weixin.qq.com/api/doc#11378"> ??????</a>
 * @throws WeixinException
 */
public ApiResult authsucc(String userId) throws WeixinException {
  String user_authsucc_uri=getRequestUri("user_authsucc_uri");
  Token token=tokenManager.getCache();
  WeixinResponse response=weixinExecutor.get(String.format(user_authsucc_uri,token.getAccessToken(),userId));
  return response.getAsResult();
}
