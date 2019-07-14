/** 
 * Gets the administrators.
 * @return administrators, returns an empty list if not found or error
 * @throws RepositoryException repository exception
 */
public List<JSONObject> getAdmins() throws RepositoryException {
  List<JSONObject> ret=userCache.getAdmins();
  if (ret.isEmpty()) {
    final Query query=new Query().setFilter(new PropertyFilter(User.USER_ROLE,FilterOperator.EQUAL,Role.ROLE_ID_C_ADMIN)).setPageCount(1).addSort(Keys.OBJECT_ID,SortDirection.ASCENDING);
    ret=getList(query);
    userCache.putAdmins(ret);
  }
  return ret;
}
