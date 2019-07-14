/** 
 * Selects the embedded track, returning a new  {@link EmbeddedSampleStream} from which the track'ssamples can be consumed.  {@link EmbeddedSampleStream#release()} must be called on the returnedstream when the track is no longer required, and before calling this method again to obtain another stream for the same track.
 * @param positionUs The current playback position in microseconds.
 * @param trackType The type of the embedded track to enable.
 * @return The {@link EmbeddedSampleStream} for the embedded track.
 */
public EmbeddedSampleStream selectEmbeddedTrack(long positionUs,int trackType){
  for (int i=0; i < embeddedSampleQueues.length; i++) {
    if (embeddedTrackTypes[i] == trackType) {
      Assertions.checkState(!embeddedTracksSelected[i]);
      embeddedTracksSelected[i]=true;
      embeddedSampleQueues[i].rewind();
      embeddedSampleQueues[i].advanceTo(positionUs,true,true);
      return new EmbeddedSampleStream(this,embeddedSampleQueues[i],i);
    }
  }
  throw new IllegalStateException();
}
