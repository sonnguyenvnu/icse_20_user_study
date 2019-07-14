/** 
 * Gets the super administrator.
 * @return super administrator
 * @throws ServiceException service exception
 */
public JSONObject getSA() throws ServiceException {
  return getAdmins().get(0);
}
