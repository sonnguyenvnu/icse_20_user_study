/** 
 * Returns a  {@link TrackOutput} that emsg messages could be written to. 
 */
public PlayerTrackEmsgHandler newPlayerTrackEmsgHandler(){
  return new PlayerTrackEmsgHandler(new SampleQueue(allocator));
}
