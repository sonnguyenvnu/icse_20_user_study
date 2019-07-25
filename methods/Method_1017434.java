/** 
 * ????
 * @param semQuery ??????
 * @return ??????
 * @see com.foxinmy.weixin4j.mp.model.SemQuery
 * @see com.foxinmy.weixin4j.mp.model.SemResult
 * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141241&token=&lang=zh_CN">????</a>
 * @throws WeixinException
 */
public SemResult semantic(SemQuery semQuery) throws WeixinException {
  String semantic_uri=getRequestUri("semantic_uri");
  Token token=tokenManager.getCache();
  WeixinResponse response=weixinExecutor.post(String.format(semantic_uri,token.getAccessToken()),semQuery.toJson());
  return response.getAsObject(new TypeReference<SemResult>(){
  }
);
}
