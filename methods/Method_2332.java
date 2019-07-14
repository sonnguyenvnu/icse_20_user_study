/** 
 * ????propertyName??????
 * @param propertyName
 * @param propertyValue
 * @return
 */
@Override protected String convertProperty(String propertyName,String propertyValue){
  for (  String p : propertyNames) {
    if (p.equalsIgnoreCase(propertyName)) {
      return AESUtil.aesDecode(propertyValue);
    }
  }
  return super.convertProperty(propertyName,propertyValue);
}
