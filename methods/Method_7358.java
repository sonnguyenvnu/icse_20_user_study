@Override public void onCreateIncomingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount,ConnectionRequest request){
  if (BuildVars.LOGS_ENABLED)   FileLog.e("onCreateIncomingConnectionFailed ");
  if (VoIPBaseService.getSharedInstance() != null) {
    VoIPBaseService.getSharedInstance().callFailedFromConnectionService();
  }
}
