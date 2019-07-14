/** 
 * Creates an  {@link ExoPlayer} instance.
 * @param context A {@link Context}.
 * @param renderers The {@link Renderer}s that will be used by the instance.
 * @param trackSelector The {@link TrackSelector} that will be used by the instance.
 */
public static ExoPlayer newInstance(Context context,Renderer[] renderers,TrackSelector trackSelector){
  return newInstance(context,renderers,trackSelector,new DefaultLoadControl());
}
