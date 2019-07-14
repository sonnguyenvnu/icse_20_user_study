/** 
 * ???????????????????????OWNER,CONTACT,ADMIN?
 * @param table
 * @param method
 * @param role
 * @return
 * @throws Exception 
 * @see {@link zuo.biao.apijson.JSONObject#KEY_ROLE} 
 */
public void verifyRole(String table,RequestMethod method,RequestRole role) throws Exception {
  Log.d(TAG,"verifyRole  table = " + table + "; method = " + method + "; role = " + role);
  if (table != null) {
    if (method == null) {
      method=GET;
    }
    if (role == null) {
      role=RequestRole.UNKNOWN;
    }
    Map<RequestMethod,RequestRole[]> map=ACCESS_MAP.get(table);
    if (map == null || Arrays.asList(map.get(method)).contains(role) == false) {
      throw new IllegalAccessException(table + " ??? " + role.name() + " ??? " + method.name() + " ???");
    }
  }
}
