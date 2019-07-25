/** 
 * Encode the map as a query string, suitable for storing in w:/sdtPr/w:tag Note that if you intend to use unmarshalString on this, you'll first need to encode the '&' as '&amp;'
 * @param map
 * @return
 */
public static String create(Map<String,String> map){
  StringBuffer sb=new StringBuffer();
  Iterator iterator=map.keySet().iterator();
  int pos=0;
  while (iterator.hasNext()) {
    String key=(String)iterator.next();
    if (pos > 0) {
      sb.append("&");
    }
    sb.append(key + "=" + (String)map.get(key));
    pos++;
  }
  return sb.toString();
}
