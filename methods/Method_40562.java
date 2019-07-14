/** 
 * Returns the parent qname of  {@code qname} -- everything up to thelast dot (exclusive), or if there are no dots, the empty string.
 */
public static String getQnameParent(String qname){
  if (qname == null || qname.isEmpty()) {
    return "";
  }
  int index=qname.lastIndexOf(".");
  if (index == -1) {
    return "";
  }
  return qname.substring(0,index);
}
