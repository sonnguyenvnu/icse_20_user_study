/** 
 * Returns the  {@link C.AudioUsage} corresponding to the specified {@link C.StreamType}.
 */
@C.AudioUsage public static int getAudioUsageForStreamType(@C.StreamType int streamType){
switch (streamType) {
case C.STREAM_TYPE_ALARM:
    return C.USAGE_ALARM;
case C.STREAM_TYPE_DTMF:
  return C.USAGE_VOICE_COMMUNICATION_SIGNALLING;
case C.STREAM_TYPE_NOTIFICATION:
return C.USAGE_NOTIFICATION;
case C.STREAM_TYPE_RING:
return C.USAGE_NOTIFICATION_RINGTONE;
case C.STREAM_TYPE_SYSTEM:
return C.USAGE_ASSISTANCE_SONIFICATION;
case C.STREAM_TYPE_VOICE_CALL:
return C.USAGE_VOICE_COMMUNICATION;
case C.STREAM_TYPE_USE_DEFAULT:
case C.STREAM_TYPE_MUSIC:
default :
return C.USAGE_MEDIA;
}
}
