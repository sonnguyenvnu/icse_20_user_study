@Override public void onCreateOutgoingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount,ConnectionRequest request){
  if (BuildVars.LOGS_ENABLED)   FileLog.e("onCreateOutgoingConnectionFailed ");
  if (VoIPBaseService.getSharedInstance() != null) {
    VoIPBaseService.getSharedInstance().callFailedFromConnectionService();
  }
}
