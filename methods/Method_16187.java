public boolean hasPermission(UserSettingPermission... permissions){
  if (permission == null) {
    return true;
  }
  if (permission == UserSettingPermission.NONE) {
    return false;
  }
  return permission.in(permissions);
}
