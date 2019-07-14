/** 
 * ????
 * @return
 */
public static int getCode(JSONObject reponse){
  try {
    return reponse.getIntValue(KEY_CODE);
  }
 catch (  Exception e) {
  }
  return 0;
}
