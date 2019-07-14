/** 
 * Sets the color table for videos with indexed color models.
 * @param track The track number.
 * @param icm IndexColorModel. Specify null to use the standard Macintoshcolor table.
 */
public void setVideoColorTable(int track,IndexColorModel icm){
  VideoTrack t=(VideoTrack)tracks.get(track);
  t.videoColorTable=icm;
}
