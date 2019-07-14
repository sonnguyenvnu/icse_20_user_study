/** 
 * add by Along
 * @param name
 * @param request
 * @param defaultValue ???
 * @return
 */
public String getStringByRequest(String name,HttpServletRequest request,String defaultValue){
  String resultStr=request.getParameter(name);
  if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
    return defaultValue;
  }
 else {
    try {
      String decode=URLDecoder.decode(resultStr,UTF_8);
      return decode;
    }
 catch (    UnsupportedEncodingException e) {
      logger.info(e);
      return defaultValue;
    }
  }
}
