/** 
 * Sets the sync interval for the specified video track. 
 */
public void setSyncInterval(int track,int i){
  ((VideoTrack)tracks.get(track)).syncInterval=i;
}
