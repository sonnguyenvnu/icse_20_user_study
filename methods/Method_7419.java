public void onCallUpdated(TLRPC.PhoneCall call){
  if (this.call == null) {
    pendingUpdates.add(call);
    return;
  }
  if (call == null)   return;
  if (call.id != this.call.id) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.w("onCallUpdated called with wrong call id (got " + call.id + ", expected " + this.call.id + ")");
    }
    return;
  }
  if (call.access_hash == 0)   call.access_hash=this.call.access_hash;
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("Call updated: " + call);
    dumpCallObject();
  }
  this.call=call;
  if (call instanceof TLRPC.TL_phoneCallDiscarded) {
    needSendDebugLog=call.need_debug;
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("call discarded, stopping service");
    }
    if (call.reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy) {
      dispatchStateChanged(STATE_BUSY);
      playingSound=true;
      soundPool.play(spBusyId,1,1,0,-1,1);
      AndroidUtilities.runOnUIThread(afterSoundRunnable,1500);
      endConnectionServiceCall(1500);
      stopSelf();
    }
 else {
      callEnded();
    }
    if (call.need_rating || forceRating || (controller != null && VoIPServerConfig.getBoolean("bad_call_rating",true) && controller.needRate())) {
      startRatingActivity();
    }
    if (debugLog == null && controller != null) {
      debugLog=controller.getDebugLog();
    }
    if (needSendDebugLog && debugLog != null) {
      TLRPC.TL_phone_saveCallDebug req=new TLRPC.TL_phone_saveCallDebug();
      req.debug=new TLRPC.TL_dataJSON();
      req.debug.data=debugLog;
      req.peer=new TLRPC.TL_inputPhoneCall();
      req.peer.access_hash=call.access_hash;
      req.peer.id=call.id;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
        @Override public void run(        TLObject response,        TLRPC.TL_error error){
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("Sent debug logs, response=" + response);
          }
        }
      }
);
    }
  }
 else   if (call instanceof TLRPC.TL_phoneCall && authKey == null) {
    if (call.g_a_or_b == null) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.w("stopping VoIP service, Ga == null");
      }
      callFailed();
      return;
    }
    if (!Arrays.equals(g_a_hash,Utilities.computeSHA256(call.g_a_or_b,0,call.g_a_or_b.length))) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.w("stopping VoIP service, Ga hash doesn't match");
      }
      callFailed();
      return;
    }
    g_a=call.g_a_or_b;
    BigInteger g_a=new BigInteger(1,call.g_a_or_b);
    BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
    if (!Utilities.isGoodGaAndGb(g_a,p)) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.w("stopping VoIP service, bad Ga and Gb (accepting)");
      }
      callFailed();
      return;
    }
    g_a=g_a.modPow(new BigInteger(1,a_or_b),p);
    byte[] authKey=g_a.toByteArray();
    if (authKey.length > 256) {
      byte[] correctedAuth=new byte[256];
      System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
      authKey=correctedAuth;
    }
 else     if (authKey.length < 256) {
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
    VoIPService.this.authKey=authKey;
    keyFingerprint=Utilities.bytesToLong(authKeyId);
    if (keyFingerprint != call.key_fingerprint) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.w("key fingerprints don't match");
      }
      callFailed();
      return;
    }
    initiateActualEncryptedCall();
  }
 else   if (call instanceof TLRPC.TL_phoneCallAccepted && authKey == null) {
    processAcceptedCall();
  }
 else {
    if (currentState == STATE_WAITING && call.receive_date != 0) {
      dispatchStateChanged(STATE_RINGING);
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("!!!!!! CALL RECEIVED");
      }
      if (connectingSoundRunnable != null) {
        AndroidUtilities.cancelRunOnUIThread(connectingSoundRunnable);
        connectingSoundRunnable=null;
      }
      if (spPlayID != 0)       soundPool.stop(spPlayID);
      spPlayID=soundPool.play(spRingbackID,1,1,0,-1,1);
      if (timeoutRunnable != null) {
        AndroidUtilities.cancelRunOnUIThread(timeoutRunnable);
        timeoutRunnable=null;
      }
      timeoutRunnable=new Runnable(){
        @Override public void run(){
          timeoutRunnable=null;
          declineIncomingCall(DISCARD_REASON_MISSED,null);
        }
      }
;
      AndroidUtilities.runOnUIThread(timeoutRunnable,MessagesController.getInstance(currentAccount).callRingTimeout);
    }
  }
}
