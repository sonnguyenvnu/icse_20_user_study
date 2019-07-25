/** 
 * ??????
 */
public void execute(PermissionsCallback permissionsCallback){
  if (permissionsCallback == null) {
    throw new IllegalArgumentException("PermissionsUtil.execute -> parameter permissionsCallback must not be null");
  }
  requestImplementation(mPermissions,permissionsCallback);
}
