@Override public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerPhoneAccount,ConnectionRequest request){
  if (BuildVars.LOGS_ENABLED)   FileLog.d("onCreateOutgoingConnection ");
  Bundle extras=request.getExtras();
  if (extras.getInt("call_type") == 1) {
    VoIPService svc=VoIPService.getSharedInstance();
    if (svc == null)     return null;
    return svc.getConnectionAndStartCall();
  }
 else   if (extras.getInt("call_type") == 2) {
  }
  return null;
}
