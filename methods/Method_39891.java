/** 
 * Reads HTTP request body using the request reader. Once body is read, it cannot be read again!
 */
public static String readRequestBodyFromReader(final HttpServletRequest request) throws IOException {
  BufferedReader buff=request.getReader();
  StringWriter out=new StringWriter();
  StreamUtil.copy(buff,out);
  return out.toString();
}
