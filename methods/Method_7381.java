protected void callFailed(){
  callFailed(controller != null && controllerStarted ? controller.getLastError() : VoIPController.ERROR_UNKNOWN);
}
