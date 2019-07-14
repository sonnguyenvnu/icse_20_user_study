/** 
 * Returns the media sequence number of the segment to load next in  {@code mediaPlaylist}.
 * @param previous The last (at least partially) loaded segment.
 * @param switchingVariant Whether the segment to load is not preceded by a segment in the samevariant.
 * @param mediaPlaylist The media playlist to which the segment to load belongs.
 * @param startOfPlaylistInPeriodUs The start of {@code mediaPlaylist} relative to the periodstart in microseconds.
 * @param loadPositionUs The current load position relative to the period start in microseconds.
 * @return The media sequence of the segment to load.
 */
private long getChunkMediaSequence(@Nullable HlsMediaChunk previous,boolean switchingVariant,HlsMediaPlaylist mediaPlaylist,long startOfPlaylistInPeriodUs,long loadPositionUs){
  if (previous == null || switchingVariant) {
    long endOfPlaylistInPeriodUs=startOfPlaylistInPeriodUs + mediaPlaylist.durationUs;
    long targetPositionInPeriodUs=(previous == null || independentSegments) ? loadPositionUs : previous.startTimeUs;
    if (!mediaPlaylist.hasEndTag && targetPositionInPeriodUs >= endOfPlaylistInPeriodUs) {
      return mediaPlaylist.mediaSequence + mediaPlaylist.segments.size();
    }
    long targetPositionInPlaylistUs=targetPositionInPeriodUs - startOfPlaylistInPeriodUs;
    return Util.binarySearchFloor(mediaPlaylist.segments,targetPositionInPlaylistUs,true,!playlistTracker.isLive() || previous == null) + mediaPlaylist.mediaSequence;
  }
  return previous.getNextChunkIndex();
}
