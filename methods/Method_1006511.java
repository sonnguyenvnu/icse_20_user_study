/** 
 * ???????????????map????? use: format("my name is ${name}, and i like ${like}!", {"name":"L.cm", "like": "Java"})
 * @param s		????????
 * @param map	??????????
 * @return		{String}???????
 */
public static String format(String s,Map<String,String> map){
  StringBuilder sb=new StringBuilder((int)(s.length() * 1.5));
  int cursor=0;
  for (int start, end; (start=s.indexOf("${",cursor)) != -1 && (end=s.indexOf('}',start)) != -1; ) {
    sb.append(s.substring(cursor,start));
    String key=s.substring(start + 2,end);
    sb.append(map.get(StringUtils.trim(key)));
    cursor=end + 1;
  }
  sb.append(s.substring(cursor,s.length()));
  return sb.toString();
}
