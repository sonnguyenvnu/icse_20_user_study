/** 
 * ?????
 * @param fullName name ? name:alias
 * @param formatAt ???? @ ? @a => a
 * @param formatColon ????? : ? A:b => b
 * @param formatHyphen ????? - ? A-b-cd-Efg => aBCdEfg
 * @param firstCase ???????????????????? Ab => ab ; A-b-Cd => aBCd
 * @return name => name; name:alias => alias
 */
public static String formatKey(String fullName,boolean formatColon,boolean formatAt,boolean formatHyphen,boolean firstCase){
  if (fullName == null) {
    Log.w(TAG,"formatKey  fullName == null >> return null;");
    return null;
  }
  if (formatColon) {
    fullName=formatColon(fullName);
  }
  if (formatAt) {
    fullName=formatAt(fullName);
  }
  if (formatHyphen) {
    fullName=formatHyphen(fullName,firstCase);
  }
  return firstCase ? StringUtil.firstCase(fullName) : fullName;
}
