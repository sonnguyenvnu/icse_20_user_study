/** 
 * A-b-cd-Efg => ABCdEfg
 * @param key
 * @return
 */
public static String formatHyphen(@NotNull String key,boolean firstCase){
  boolean first=true;
  int index;
  String name="";
  String part;
  do {
    index=key.indexOf("-");
    part=index < 0 ? key : key.substring(0,index);
    name+=firstCase && first == false ? StringUtil.firstCase(part,true) : part;
    key=key.substring(index + 1);
    first=false;
  }
 while (index >= 0);
  return name;
}
