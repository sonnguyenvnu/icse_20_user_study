/** 
 * Initializes variables. Aborts with an  {@link UnauthorizedAccessException} if the user is notlogged in or if a non-admin tried to masquerade as another user.
 */
public void init(HttpServletRequest req){
  initialiseAttributes(req);
  authenticateUser();
}
