/** 
 * @param roleId ??id {@link Role#getId()}
 * @return ????????
 */
default boolean hasRole(String roleId){
  return getRole(roleId).isPresent();
}
