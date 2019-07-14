private boolean allPermissionsGranted(){
  for (  String permission : getRequiredPermissions()) {
    if (!isPermissionGranted(this,permission)) {
      return false;
    }
  }
  return true;
}
