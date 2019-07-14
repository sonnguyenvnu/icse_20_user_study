/** 
 * Gets an attribute of the specified HTTP session by the given session.
 * @param session       the given session
 * @param attributeName the specified attribute name
 * @return attribute, returns {@code null} if not found or occurred exception
 */
public static Object getHttpSessionAttribute(final Session session,final String attributeName){
  final HttpSession httpSession=(HttpSession)session.getUserProperties().get(HttpSession.class.getName());
  if (null == httpSession) {
    return null;
  }
  try {
    return httpSession.getAttribute(attributeName);
  }
 catch (  final Exception e) {
    return null;
  }
}
