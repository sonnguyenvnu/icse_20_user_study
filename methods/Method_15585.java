/** 
 * "key":null????????FastJSON?????null
 * @param pair leftKey:rightValue
 * @param isRightValueDefault ???????pair??? : ?????pair?leftKey?false-??
 * @param defaultValue ???
 * @return @NonNull
 */
public static Entry<String,String> parseEntry(String pair,boolean isRightValueDefault,String defaultValue){
  pair=StringUtil.getString(pair);
  Entry<String,String> entry=new Entry<String,String>();
  if (pair.isEmpty() == false) {
    int index=pair.indexOf(":");
    if (index < 0) {
      entry.setKey(isRightValueDefault ? pair : defaultValue);
      entry.setValue(isRightValueDefault ? defaultValue : pair);
    }
 else {
      entry.setKey(pair.substring(0,index));
      entry.setValue(pair.substring(index + 1,pair.length()));
    }
  }
  return entry;
}
