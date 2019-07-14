/** 
 * ?NameValuePairs????????
 * @param nameValues
 * @return
 */
protected String toString(NameValuePair[] nameValues){
  if (nameValues == null || nameValues.length == 0) {
    return "null";
  }
  StringBuffer buffer=new StringBuffer();
  for (int i=0; i < nameValues.length; i++) {
    NameValuePair nameValue=nameValues[i];
    if (i == 0) {
      buffer.append(nameValue.getName() + "=" + nameValue.getValue());
    }
 else {
      buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
    }
  }
  return buffer.toString();
}
