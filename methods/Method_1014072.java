@Override public void failed(String errorMessageKey,Object... arguments){
  if (this.state == InternalState.FINISHED) {
    throw new IllegalStateException("Update is finished.");
  }
  if (errorMessageKey == null || errorMessageKey.isEmpty()) {
    throw new IllegalArgumentException("The error message key must not be null or empty.");
  }
  this.state=InternalState.FINISHED;
  String errorMessage=getMessage(firmwareUpdateHandler.getClass(),errorMessageKey,arguments);
  postResultInfoEvent(FirmwareUpdateResult.ERROR,errorMessage);
}
