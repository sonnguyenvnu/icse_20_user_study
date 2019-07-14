/** 
 * Reads request body only once and returns it to user.
 */
public String readRequestBody(){
  if (requestBody == null) {
    try {
      requestBody=ServletUtil.readRequestBodyFromStream(getHttpServletRequest());
    }
 catch (    IOException ioex) {
      requestBody=StringPool.EMPTY;
    }
  }
  return requestBody;
}
