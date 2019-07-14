/** 
 * Gets value of data field or <code>null</code> if field not found.
 */
private String getDataFieldValue(final String dataHeader,final String fieldName){
  String value=null;
  String token=String.valueOf((new StringBuffer(String.valueOf(fieldName))).append('=').append('"'));
  int pos=dataHeader.indexOf(token);
  if (pos > 0) {
    int start=pos + token.length();
    int end=dataHeader.indexOf('"',start);
    if ((start > 0) && (end > 0)) {
      value=dataHeader.substring(start,end);
    }
  }
  return value;
}
