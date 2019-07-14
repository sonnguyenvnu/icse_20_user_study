/** 
 * Notifies the media clock that a renderer has been enabled. Starts using the media clock of the provided renderer if available.
 * @param renderer The renderer which has been enabled.
 * @throws ExoPlaybackException If the renderer provides a media clock and another renderer mediaclock is already provided.
 */
public void onRendererEnabled(Renderer renderer) throws ExoPlaybackException {
  MediaClock rendererMediaClock=renderer.getMediaClock();
  if (rendererMediaClock != null && rendererMediaClock != rendererClock) {
    if (rendererClock != null) {
      throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
    }
    this.rendererClock=rendererMediaClock;
    this.rendererClockSource=renderer;
    rendererClock.setPlaybackParameters(standaloneMediaClock.getPlaybackParameters());
    ensureSynced();
  }
}
