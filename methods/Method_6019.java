/** 
 * Returns the audio track channel configuration for the given channel count, or  {@link AudioFormat#CHANNEL_INVALID} if output is not poossible.
 * @param channelCount The number of channels in the input audio.
 * @return The channel configuration or {@link AudioFormat#CHANNEL_INVALID} if output is notpossible.
 */
public static int getAudioTrackChannelConfig(int channelCount){
switch (channelCount) {
case 1:
    return AudioFormat.CHANNEL_OUT_MONO;
case 2:
  return AudioFormat.CHANNEL_OUT_STEREO;
case 3:
return AudioFormat.CHANNEL_OUT_STEREO | AudioFormat.CHANNEL_OUT_FRONT_CENTER;
case 4:
return AudioFormat.CHANNEL_OUT_QUAD;
case 5:
return AudioFormat.CHANNEL_OUT_QUAD | AudioFormat.CHANNEL_OUT_FRONT_CENTER;
case 6:
return AudioFormat.CHANNEL_OUT_5POINT1;
case 7:
return AudioFormat.CHANNEL_OUT_5POINT1 | AudioFormat.CHANNEL_OUT_BACK_CENTER;
case 8:
if (Util.SDK_INT >= 23) {
return AudioFormat.CHANNEL_OUT_7POINT1_SURROUND;
}
 else if (Util.SDK_INT >= 21) {
return AudioFormat.CHANNEL_OUT_5POINT1 | AudioFormat.CHANNEL_OUT_SIDE_LEFT | AudioFormat.CHANNEL_OUT_SIDE_RIGHT;
}
 else {
return AudioFormat.CHANNEL_INVALID;
}
default :
return AudioFormat.CHANNEL_INVALID;
}
}
