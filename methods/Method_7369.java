public long getCallDuration(){
  if (!controllerStarted || controller == null)   return lastKnownDuration;
  return lastKnownDuration=controller.getCallDuration();
}
