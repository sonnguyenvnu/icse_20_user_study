/** 
 * Creates a  {@link SimpleExoPlayer} instance.
 * @param context A {@link Context}.
 * @param renderersFactory A factory for creating {@link Renderer}s to be used by the instance.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 * @param drmSessionManager An optional {@link DrmSessionManager}. May be null if the instance will not be used for DRM protected playbacks.
 * @param looper The {@link Looper} which must be used for all calls to the player and which isused to call listeners on.
 */
public static SimpleExoPlayer newSimpleInstance(Context context,RenderersFactory renderersFactory,TrackSelector trackSelector,LoadControl loadControl,@Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,Looper looper){
  return newSimpleInstance(context,renderersFactory,trackSelector,loadControl,drmSessionManager,new AnalyticsCollector.Factory(),looper);
}
