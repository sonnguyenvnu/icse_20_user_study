/** 
 * ?????????????
 */
private void ensure(String[] permissions){
  if (permissions == null || permissions.length == 0) {
    throw new IllegalArgumentException("PermissionsUtil.request -> requestEach requires at least one input permission");
  }
}
