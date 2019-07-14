/** 
 * Sends correct headers to require basic authentication for the given realm.
 */
public static void requireAuthentication(final HttpServletResponse resp,final String realm) throws IOException {
  resp.setHeader(WWW_AUTHENTICATE,"Basic realm=\"" + realm + '\"');
  resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
}
