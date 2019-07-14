/** 
 * Converts  {@link AudioAttributes} to one of the audio focus request.<p>This follows the class Javadoc of  {@link AudioFocusRequest}.
 * @param audioAttributes The audio attributes associated with this focus request.
 * @return The type of audio focus gain that should be requested.
 */
private static int convertAudioAttributesToFocusGain(@Nullable AudioAttributes audioAttributes){
  if (audioAttributes == null) {
    return C.AUDIOFOCUS_NONE;
  }
switch (audioAttributes.usage) {
case C.USAGE_VOICE_COMMUNICATION_SIGNALLING:
    return C.AUDIOFOCUS_NONE;
case C.USAGE_GAME:
case C.USAGE_MEDIA:
  return C.AUDIOFOCUS_GAIN;
case C.USAGE_UNKNOWN:
Log.w(TAG,"Specify a proper usage in the audio attributes for audio focus" + " handling. Using AUDIOFOCUS_GAIN by default.");
return C.AUDIOFOCUS_GAIN;
case C.USAGE_ALARM:
case C.USAGE_VOICE_COMMUNICATION:
return C.AUDIOFOCUS_GAIN_TRANSIENT;
case C.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE:
case C.USAGE_ASSISTANCE_SONIFICATION:
case C.USAGE_NOTIFICATION:
case C.USAGE_NOTIFICATION_COMMUNICATION_DELAYED:
case C.USAGE_NOTIFICATION_COMMUNICATION_INSTANT:
case C.USAGE_NOTIFICATION_COMMUNICATION_REQUEST:
case C.USAGE_NOTIFICATION_EVENT:
case C.USAGE_NOTIFICATION_RINGTONE:
return C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
case C.USAGE_ASSISTANT:
if (Util.SDK_INT >= 19) {
return C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE;
}
 else {
return C.AUDIOFOCUS_GAIN_TRANSIENT;
}
case C.USAGE_ASSISTANCE_ACCESSIBILITY:
if (audioAttributes.contentType == C.CONTENT_TYPE_SPEECH) {
return C.AUDIOFOCUS_GAIN_TRANSIENT;
}
return C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
default :
Log.w(TAG,"Unidentified audio usage: " + audioAttributes.usage);
return C.AUDIOFOCUS_NONE;
}
}
