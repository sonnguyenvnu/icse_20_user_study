/** 
 * ????id??????,????????null
 * @param id ??id
 * @return ????
 */
default Optional<Permission> getPermission(String id){
  if (null == id) {
    return Optional.empty();
  }
  return getPermissions().stream().filter(permission -> permission.getId().equals(id)).findAny();
}
