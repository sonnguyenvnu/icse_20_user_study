/** 
 * ??xml??
 * @param arr
 * @return
 */
public static String arrayToXml(HashMap<String,String> arr){
  String xml="<xml>";
  Iterator<Entry<String,String>> iter=arr.entrySet().iterator();
  while (iter.hasNext()) {
    Entry<String,String> entry=iter.next();
    String key=entry.getKey();
    String val=entry.getValue();
    if (isNumeric(val)) {
      xml+="<" + key + ">" + val + "</" + key + ">";
    }
 else     xml+="<" + key + "><![CDATA[" + val + "]]></" + key + ">";
  }
  xml+="</xml>";
  return xml;
}
