/** 
 * ?Map????,???Json??,???
 * @param response
 * @param object
 * @throws IOException
 */
public static void responseJson(HttpServletResponse response,Object object) throws IOException {
  Object toJSON=JSONObject.toJSON(object);
  try {
    response.getWriter().write(toJSON.toString());
  }
 catch (  IOException e) {
    LOG.error(e);
  }
}
