/** 
 * Creates an  {@link ExoPlayer} instance.
 * @param context A {@link Context}.
 * @param renderers The {@link Renderer}s that will be used by the instance.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 * @param bandwidthMeter The {@link BandwidthMeter} that will be used by the instance.
 * @param looper The {@link Looper} which must be used for all calls to the player and which isused to call listeners on.
 */
@SuppressWarnings("unused") public static ExoPlayer newInstance(Context context,Renderer[] renderers,TrackSelector trackSelector,LoadControl loadControl,BandwidthMeter bandwidthMeter,Looper looper){
  return new ExoPlayerImpl(renderers,trackSelector,loadControl,bandwidthMeter,Clock.DEFAULT,looper);
}
