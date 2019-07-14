/** 
 * ?????????
 * @param paramMap ?????sign????????
 * @param payKey   ????
 * @return ??????
 */
private final static String getSignTemp(Map<String,Object> paramMap,String payKey){
  ArrayList<String> keyList=new ArrayList<>(paramMap.keySet());
  Collections.sort(keyList);
  StringBuilder signParam=new StringBuilder();
  for (  String key : keyList) {
    if (!"sign".equals(key) && StringUtil.isNotNull(paramMap.get(key))) {
      signParam.append(key).append("=").append(paramMap.get(key)).append("&");
    }
  }
  signParam.delete(signParam.length() - 1,signParam.length());
  logger.info("?????{}",signParam.toString());
  signParam.append("&key=").append(payKey);
  return signParam.toString();
}
