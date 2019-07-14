/** 
 * Returns a boolean that determines if there is a currently logged in user or not.
 * @deprecated Prefer {@link #observable()}
 */
@Deprecated public boolean exists(){
  return getUser() != null;
}
