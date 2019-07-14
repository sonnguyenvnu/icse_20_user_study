/** 
 * ??????
 * @param key
 * @return
 */
public boolean isContain(String key){
  if (StringUtil.isNotEmpty(key,true) == false) {
    Log.e(TAG,"isContain StringUtil.isNotEmpty(key, true) == false >> return false;");
    return false;
  }
  return sp.contains(StringUtil.getTrimedString(key));
}
