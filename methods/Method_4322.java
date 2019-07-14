/** 
 * Returns a newly generated audio session identifier, or  {@link AudioManager#ERROR} if an erroroccurred in which case audio playback may fail.
 * @see AudioManager#generateAudioSessionId()
 */
@TargetApi(21) public static int generateAudioSessionIdV21(Context context){
  return ((AudioManager)context.getSystemService(Context.AUDIO_SERVICE)).generateAudioSessionId();
}
