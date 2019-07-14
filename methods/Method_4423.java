/** 
 * Creates an  {@link ExoPlayer} instance.
 * @param context A {@link Context}.
 * @param renderers The {@link Renderer}s that will be used by the instance.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 * @param looper The {@link Looper} which must be used for all calls to the player and which isused to call listeners on.
 */
public static ExoPlayer newInstance(Context context,Renderer[] renderers,TrackSelector trackSelector,LoadControl loadControl,Looper looper){
  return newInstance(context,renderers,trackSelector,loadControl,getDefaultBandwidthMeter(context),looper);
}
