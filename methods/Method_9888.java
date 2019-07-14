/** 
 * Gets role-permission relations by the specified role id.
 * @param roleId the specified role id
 * @return for example      <pre>[{ "oId": "", "roleId": roleId, "permissionId": "" }, ....], returns an empty list if not found </pre>
 * @throws RepositoryException repository exception
 */
public List<JSONObject> getByRoleId(final String roleId) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Role.ROLE_ID,FilterOperator.EQUAL,roleId)).setPageCount(1);
  return getList(query);
}
