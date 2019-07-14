void callFailedFromConnectionService(){
  if (isOutgoing)   callFailed(VoIPController.ERROR_CONNECTION_SERVICE);
 else   hangUp();
}
