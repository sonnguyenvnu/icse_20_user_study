/** 
 * Creates an instance of type  {@link #TYPE_RENDERER}.
 * @param cause The cause of the failure.
 * @param rendererIndex The index of the renderer in which the failure occurred.
 * @return The created instance.
 */
public static ExoPlaybackException createForRenderer(Exception cause,int rendererIndex){
  return new ExoPlaybackException(TYPE_RENDERER,cause,rendererIndex);
}
