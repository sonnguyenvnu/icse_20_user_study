/** 
 * Gets the current template dir from thread local data.
 * @return template dir, returns "classic" if not found
 */
public static String getTemplateDir(){
  JSONObject data=THREAD_LOCAL_DATA.get();
  if (null == data) {
    return "classic";
  }
  return data.optString(Keys.TEMAPLTE_DIR_NAME);
}
