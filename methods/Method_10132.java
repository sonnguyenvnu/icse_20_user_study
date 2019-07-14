/** 
 * Gets the current user.
 * @param request the specified request
 * @return the current user, {@code null} if not found
 * @throws ServiceException service exception
 */
public JSONObject getCurrentUser(final HttpServletRequest request) throws ServiceException {
  final JSONObject currentUser=Sessions.currentUser(request);
  if (null == currentUser) {
    return null;
  }
  final String id=currentUser.optString(Keys.OBJECT_ID);
  return getUser(id);
}
