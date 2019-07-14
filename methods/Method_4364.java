private void onKeysError(Exception e){
  if (e instanceof NotProvisionedException) {
    provisioningManager.provisionRequired(this);
  }
 else {
    onError(e);
  }
}
