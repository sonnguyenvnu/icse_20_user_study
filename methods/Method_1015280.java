/** 
 * ???????????????map?????,
 * @param s   ????????
 * @param map ??????????
 * @return ???????
 */
public static String replace(String s,Map<String,String> map){
  StringBuilder sb=new StringBuilder((int)(s.length() * 1.5));
  int cursor=0;
  String str="${";
  char ch='}';
  for (int start, end; (start=s.indexOf(str,cursor)) != -1 && (end=s.indexOf(ch,start)) != -1; ) {
    sb.append(s.substring(cursor,start));
    String key=s.substring(start + 2,end);
    sb.append(map.get(StringUtils.trim(key)));
    cursor=end + 1;
  }
  sb.append(s.substring(cursor,s.length()));
  return sb.toString();
}
