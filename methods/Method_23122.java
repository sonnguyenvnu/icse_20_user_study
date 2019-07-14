/** 
 * Gets the sync interval from the specified video track. 
 */
public int getSyncInterval(int track){
  return ((VideoTrack)tracks.get(track)).syncInterval;
}
