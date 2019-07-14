/** 
 * ???????????
 * @param map
 * @param keyName
 * @param keyValue
 * @param sign
 */
public static boolean validate(Map<String,String> map,String keyName,String keyValue,String sign){
  if (StringUtils.isBlank(sign)) {
    return false;
  }
  String resultSign=getSign(map,keyName,keyValue);
  if (sign.toUpperCase().equals(resultSign.toUpperCase())) {
    return true;
  }
  return false;
}
