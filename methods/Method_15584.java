/** 
 * isRightValueDefault = false; "key":null????????FastJSON?????null
 * @param pair leftKey:rightValue
 * @param isRightValueDefault ???????pair??? : ?????pair?leftKey?false-??
 * @return {@link #parseEntry(String,boolean,String)}
 */
public static Entry<String,String> parseEntry(String pair,boolean isRightValueDefault){
  return parseEntry(pair,isRightValueDefault,null);
}
