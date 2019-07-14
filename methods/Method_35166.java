private void setAttached(){
  if (!attached) {
    attached=true;
    for (int i=pendingPermissionRequests.size() - 1; i >= 0; i--) {
      PendingPermissionRequest request=pendingPermissionRequests.remove(i);
      requestPermissions(request.instanceId,request.permissions,request.requestCode);
    }
  }
  for (  ActivityHostedRouter router : new ArrayList<>(routerMap.values())) {
    router.onContextAvailable();
  }
}
