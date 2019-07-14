/** 
 * Creates a message for the exception.
 * @param fieldName  the field name
 * @param value  the value rejected
 * @return the message
 */
private static String createMessage(String fieldName,String value){
  StringBuffer buf=new StringBuffer().append("Value ");
  if (value == null) {
    buf.append("null");
  }
 else {
    buf.append('"');
    buf.append(value);
    buf.append('"');
  }
  buf.append(" for ").append(fieldName).append(' ').append("is not supported");
  return buf.toString();
}
