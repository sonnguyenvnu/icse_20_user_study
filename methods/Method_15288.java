/** 
 * ?????????
 * @param object
 * @return
 */
public static String format(Object object){
  try {
    return com.alibaba.fastjson.JSON.toJSONString(object,true);
  }
 catch (  Exception e) {
    Log.e(TAG,"format  catch \n" + e.getMessage());
  }
  return null;
}
