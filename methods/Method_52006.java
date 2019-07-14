/** 
 * Restricts the roles covered by the mask to the parameters.
 * @param roles The roles to cover
 */
public void restrictRolesTo(JavaOperationSignature.Role... roles){
  roleMask.clear();
  roleMask.addAll(Arrays.asList(roles));
}
