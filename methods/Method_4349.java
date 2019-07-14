/** 
 * Builds video renderers for use by the player.
 * @param context The {@link Context} associated with the player.
 * @param drmSessionManager An optional {@link DrmSessionManager}. May be null if the player will not be used for DRM protected playbacks.
 * @param allowedVideoJoiningTimeMs The maximum duration in milliseconds for which videorenderers can attempt to seamlessly join an ongoing playback.
 * @param eventHandler A handler associated with the main thread's looper.
 * @param eventListener An event listener.
 * @param extensionRendererMode The extension renderer mode.
 * @param out An array to which the built renderers should be appended.
 */
protected void buildVideoRenderers(Context context,@Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,long allowedVideoJoiningTimeMs,Handler eventHandler,VideoRendererEventListener eventListener,@ExtensionRendererMode int extensionRendererMode,ArrayList<Renderer> out){
  out.add(new MediaCodecVideoRenderer(context,MediaCodecSelector.DEFAULT,allowedVideoJoiningTimeMs,drmSessionManager,false,eventHandler,eventListener,MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY));
  if (extensionRendererMode == EXTENSION_RENDERER_MODE_OFF) {
    return;
  }
  int extensionRendererIndex=out.size();
  if (extensionRendererMode == EXTENSION_RENDERER_MODE_PREFER) {
    extensionRendererIndex--;
  }
  try {
    Class<?> clazz=Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer");
    Constructor<?> constructor=clazz.getConstructor(boolean.class,long.class,android.os.Handler.class,com.google.android.exoplayer2.video.VideoRendererEventListener.class,int.class);
    Renderer renderer=(Renderer)constructor.newInstance(true,allowedVideoJoiningTimeMs,eventHandler,eventListener,MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY);
    out.add(extensionRendererIndex++,renderer);
    Log.i(TAG,"Loaded LibvpxVideoRenderer.");
  }
 catch (  ClassNotFoundException e) {
  }
catch (  Exception e) {
    throw new RuntimeException("Error instantiating VP9 extension",e);
  }
}
