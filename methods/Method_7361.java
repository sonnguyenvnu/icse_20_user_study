public boolean hasEarpiece(){
  if (USE_CONNECTION_SERVICE) {
    if (systemCallConnection != null && systemCallConnection.getCallAudioState() != null) {
      int routeMask=systemCallConnection.getCallAudioState().getSupportedRouteMask();
      return (routeMask & (CallAudioState.ROUTE_EARPIECE | CallAudioState.ROUTE_WIRED_HEADSET)) != 0;
    }
  }
  if (((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getPhoneType() != TelephonyManager.PHONE_TYPE_NONE)   return true;
  if (mHasEarpiece != null) {
    return mHasEarpiece;
  }
  try {
    AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
    Method method=AudioManager.class.getMethod("getDevicesForStream",Integer.TYPE);
    Field field=AudioManager.class.getField("DEVICE_OUT_EARPIECE");
    int earpieceFlag=field.getInt(null);
    int bitmaskResult=(int)method.invoke(am,AudioManager.STREAM_VOICE_CALL);
    if ((bitmaskResult & earpieceFlag) == earpieceFlag) {
      mHasEarpiece=Boolean.TRUE;
    }
 else {
      mHasEarpiece=Boolean.FALSE;
    }
  }
 catch (  Throwable error) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Error while checking earpiece! ",error);
    }
    mHasEarpiece=Boolean.TRUE;
  }
  return mHasEarpiece;
}
