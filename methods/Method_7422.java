private void processAcceptedCall(){
  dispatchStateChanged(STATE_EXCHANGING_KEYS);
  BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
  BigInteger i_authKey=new BigInteger(1,call.g_b);
  if (!Utilities.isGoodGaAndGb(i_authKey,p)) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.w("stopping VoIP service, bad Ga and Gb");
    }
    callFailed();
    return;
  }
  i_authKey=i_authKey.modPow(new BigInteger(1,a_or_b),p);
  byte[] authKey=i_authKey.toByteArray();
  if (authKey.length > 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
    authKey=correctedAuth;
  }
 else   if (authKey.length < 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(authKey,0,correctedAuth,256 - authKey.length,authKey.length);
    for (int a=0; a < 256 - authKey.length; a++) {
      correctedAuth[a]=0;
    }
    authKey=correctedAuth;
  }
  byte[] authKeyHash=Utilities.computeSHA1(authKey);
  byte[] authKeyId=new byte[8];
  System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
  long fingerprint=Utilities.bytesToLong(authKeyId);
  this.authKey=authKey;
  keyFingerprint=fingerprint;
  TLRPC.TL_phone_confirmCall req=new TLRPC.TL_phone_confirmCall();
  req.g_a=g_a;
  req.key_fingerprint=fingerprint;
  req.peer=new TLRPC.TL_inputPhoneCall();
  req.peer.id=call.id;
  req.peer.access_hash=call.access_hash;
  req.protocol=new TLRPC.TL_phoneCallProtocol();
  req.protocol.max_layer=CALL_MAX_LAYER;
  req.protocol.min_layer=CALL_MIN_LAYER;
  req.protocol.udp_p2p=req.protocol.udp_reflector=true;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
    @Override public void run(    final TLObject response,    final TLRPC.TL_error error){
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          if (error != null) {
            callFailed();
          }
 else {
            call=((TLRPC.TL_phone_phoneCall)response).phone_call;
            initiateActualEncryptedCall();
          }
        }
      }
);
    }
  }
);
}
