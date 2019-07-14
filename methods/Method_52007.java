/** 
 * Forbid all mentioned roles.
 * @param roles The roles to forbid
 */
public void forbid(JavaOperationSignature.Role... roles){
  roleMask.removeAll(Arrays.asList(roles));
}
