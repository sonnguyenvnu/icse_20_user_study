/** 
 * Returns whether this playlist is newer than  {@code other}.
 * @param other The playlist to compare.
 * @return Whether this playlist is newer than {@code other}.
 */
public boolean isNewerThan(HlsMediaPlaylist other){
  if (other == null || mediaSequence > other.mediaSequence) {
    return true;
  }
  if (mediaSequence < other.mediaSequence) {
    return false;
  }
  int segmentCount=segments.size();
  int otherSegmentCount=other.segments.size();
  return segmentCount > otherSegmentCount || (segmentCount == otherSegmentCount && hasEndTag && !other.hasEndTag);
}
