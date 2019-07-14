/** 
 * Replaces the  {@link SampleStream} that will be associated with this renderer.<p> This method may be called when the renderer is in the following states: {@link #STATE_ENABLED},  {@link #STATE_STARTED}.
 * @param formats The enabled formats. Should be empty.
 * @param stream The {@link SampleStream} to be associated with this renderer.
 * @param offsetUs The offset that should be subtracted from {@code positionUs} in{@link #render(long,long)} to get the playback position with respect to the media.
 * @throws ExoPlaybackException If an error occurs.
 */
@Override public final void replaceStream(Format[] formats,SampleStream stream,long offsetUs) throws ExoPlaybackException {
  Assertions.checkState(!streamIsFinal);
  this.stream=stream;
  onRendererOffsetChanged(offsetUs);
}
