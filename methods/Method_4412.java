/** 
 * Creates a  {@link SimpleExoPlayer} instance.
 * @param context A {@link Context}.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 * @param drmSessionManager An optional {@link DrmSessionManager}. May be null if the instance will not be used for DRM protected playbacks.
 * @param extensionRendererMode The extension renderer mode, which determines if and how availableextension renderers are used. Note that extensions must be included in the application build for them to be considered available.
 * @param allowedVideoJoiningTimeMs The maximum duration for which a video renderer can attempt toseamlessly join an ongoing playback.
 * @deprecated Use {@link #newSimpleInstance(Context,RenderersFactory,TrackSelector,LoadControl,DrmSessionManager)}.
 */
@Deprecated public static SimpleExoPlayer newSimpleInstance(Context context,TrackSelector trackSelector,LoadControl loadControl,@Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,@DefaultRenderersFactory.ExtensionRendererMode int extensionRendererMode,long allowedVideoJoiningTimeMs){
  RenderersFactory renderersFactory=new DefaultRenderersFactory(context,extensionRendererMode,allowedVideoJoiningTimeMs);
  return newSimpleInstance(context,renderersFactory,trackSelector,loadControl,drmSessionManager);
}
