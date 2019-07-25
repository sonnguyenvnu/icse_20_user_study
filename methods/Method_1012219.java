/** 
 * ??????
 * @param tag
 */
public static void debug(String tag){
  if (!TextUtils.isEmpty(tag)) {
    setDebug(true);
    setPriority(MIN_LOG_PRIORITY);
    setTag(tag);
  }
 else {
    setDebug(false);
    setPriority(MAX_LOG_PRIORITY);
    setTag("");
  }
}
