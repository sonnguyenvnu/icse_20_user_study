private void getRuntimePermissions(){
  List<String> allNeededPermissions=new ArrayList<>();
  for (  String permission : getRequiredPermissions()) {
    if (!isPermissionGranted(this,permission)) {
      allNeededPermissions.add(permission);
    }
  }
  if (!allNeededPermissions.isEmpty()) {
    ActivityCompat.requestPermissions(this,allNeededPermissions.toArray(new String[0]),PERMISSION_REQUESTS);
  }
}
