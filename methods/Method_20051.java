private String[] getRequiredPermissions(){
  try {
    PackageInfo info=this.getPackageManager().getPackageInfo(this.getPackageName(),PackageManager.GET_PERMISSIONS);
    String[] ps=info.requestedPermissions;
    if (ps != null && ps.length > 0) {
      return ps;
    }
 else {
      return new String[0];
    }
  }
 catch (  Exception e) {
    return new String[0];
  }
}
