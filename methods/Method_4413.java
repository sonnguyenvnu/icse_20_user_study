/** 
 * Creates a  {@link SimpleExoPlayer} instance. Available extension renderers are not used.
 * @param context A {@link Context}.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 * @param drmSessionManager An optional {@link DrmSessionManager}. May be null if the instance will not be used for DRM protected playbacks.
 */
public static SimpleExoPlayer newSimpleInstance(Context context,TrackSelector trackSelector,LoadControl loadControl,@Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager){
  RenderersFactory renderersFactory=new DefaultRenderersFactory(context);
  return newSimpleInstance(context,renderersFactory,trackSelector,loadControl,drmSessionManager);
}
