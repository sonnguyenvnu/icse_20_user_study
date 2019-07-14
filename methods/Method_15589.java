/** 
 * @param method
 * @param keys
 * @return
 */
public static String getFunction(String method,String[] keys){
  String f=method + "(JSONObject request";
  if (keys != null) {
    for (int i=0; i < keys.length; i++) {
      f+=(", String " + keys[i]);
    }
  }
  f+=")";
  return f;
}
