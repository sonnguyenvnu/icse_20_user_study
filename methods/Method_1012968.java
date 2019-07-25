@Override public PermissionManager.TPermissionType invoke(InvokeParam invokeParam){
  PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
  if (PermissionManager.TPermissionType.WAIT.equals(type)) {
    this.invokeParam=invokeParam;
  }
  return type;
}
