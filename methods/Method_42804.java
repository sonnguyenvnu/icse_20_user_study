/** 
 * ?????Json????Json??
 * @param httpServletRequest
 * @return
 */
public static JSONObject requestJson(HttpServletRequest httpServletRequest){
  StringBuffer buffer=new StringBuffer();
  String line=null;
  JSONObject jsonObject=null;
  try {
    BufferedReader reader=httpServletRequest.getReader();
    while ((line=reader.readLine()) != null) {
      buffer.append(line);
    }
    reader.close();
    jsonObject=JSONObject.parseObject(buffer.toString());
  }
 catch (  Exception e) {
    LOG.error(e);
  }
  return jsonObject;
}
