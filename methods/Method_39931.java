/** 
 * Returns new or existing instance of <code>MultipartRequest</code>.
 */
public static MultipartRequest getInstance(final HttpServletRequest request) throws IOException {
  return getInstance(request,null,null);
}
