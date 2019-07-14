/** 
 * Creates a  {@link SimpleExoPlayer} instance.
 * @param context A {@link Context}.
 * @param renderersFactory A factory for creating {@link Renderer}s to be used by the instance.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 * @param loadControl The {@link LoadControl} that will be used by the instance.
 */
public static SimpleExoPlayer newSimpleInstance(Context context,RenderersFactory renderersFactory,TrackSelector trackSelector,LoadControl loadControl){
  return newSimpleInstance(context,renderersFactory,trackSelector,loadControl,null,Util.getLooper());
}
