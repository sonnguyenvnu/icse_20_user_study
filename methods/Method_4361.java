private void onProvisionResponse(Object request,Object response){
  if (request != currentProvisionRequest || (state != STATE_OPENING && !isOpen())) {
    return;
  }
  currentProvisionRequest=null;
  if (response instanceof Exception) {
    provisioningManager.onProvisionError((Exception)response);
    return;
  }
  try {
    mediaDrm.provideProvisionResponse((byte[])response);
  }
 catch (  Exception e) {
    provisioningManager.onProvisionError(e);
    return;
  }
  provisioningManager.onProvisionCompleted();
}
