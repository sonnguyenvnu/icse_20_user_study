@TargetApi(Build.VERSION_CODES.M) public void requestPermissions(@NonNull String instanceId,@NonNull String[] permissions,int requestCode){
  if (attached) {
    permissionRequestMap.put(requestCode,instanceId);
    requestPermissions(permissions,requestCode);
  }
 else {
    pendingPermissionRequests.add(new PendingPermissionRequest(instanceId,permissions,requestCode));
  }
}
