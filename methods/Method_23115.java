/** 
 * Gets the preferred color table for displaying the movie on devices that support only 256 colors.
 * @param track The track number.
 * @return The color table or null, if the video uses the standard Macintoshcolor table.
 */
public IndexColorModel getVideoColorTable(int track){
  VideoTrack t=(VideoTrack)tracks.get(track);
  return t.videoColorTable;
}
