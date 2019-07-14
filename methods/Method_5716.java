/** 
 * Called by the player to initialize the selector.
 * @param listener An invalidation listener that the selector can call to indicate that selectionsit has previously made are no longer valid.
 * @param bandwidthMeter A bandwidth meter which can be used by track selections to select tracks.
 */
public final void init(InvalidationListener listener,BandwidthMeter bandwidthMeter){
  this.listener=listener;
  this.bandwidthMeter=bandwidthMeter;
}
