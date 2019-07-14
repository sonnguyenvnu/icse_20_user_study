/** 
 * Returns an array of  {@link MediaChunkIterator}s for upcoming media chunks.
 * @param previous The previous media chunk. May be null.
 * @param loadPositionUs The position at which the iterators will start.
 * @return Array of {@link MediaChunkIterator}s for each track.
 */
public MediaChunkIterator[] createMediaChunkIterators(@Nullable HlsMediaChunk previous,long loadPositionUs){
  int oldVariantIndex=previous == null ? C.INDEX_UNSET : trackGroup.indexOf(previous.trackFormat);
  MediaChunkIterator[] chunkIterators=new MediaChunkIterator[trackSelection.length()];
  for (int i=0; i < chunkIterators.length; i++) {
    int variantIndex=trackSelection.getIndexInTrackGroup(i);
    HlsUrl variantUrl=variants[variantIndex];
    if (!playlistTracker.isSnapshotValid(variantUrl)) {
      chunkIterators[i]=MediaChunkIterator.EMPTY;
      continue;
    }
    HlsMediaPlaylist playlist=playlistTracker.getPlaylistSnapshot(variantUrl,false);
    long startOfPlaylistInPeriodUs=playlist.startTimeUs - playlistTracker.getInitialStartTimeUs();
    boolean switchingVariant=variantIndex != oldVariantIndex;
    long chunkMediaSequence=getChunkMediaSequence(previous,switchingVariant,playlist,startOfPlaylistInPeriodUs,loadPositionUs);
    if (chunkMediaSequence < playlist.mediaSequence) {
      chunkIterators[i]=MediaChunkIterator.EMPTY;
      continue;
    }
    int chunkIndex=(int)(chunkMediaSequence - playlist.mediaSequence);
    chunkIterators[i]=new HlsMediaPlaylistSegmentIterator(playlist,startOfPlaylistInPeriodUs,chunkIndex);
  }
  return chunkIterators;
}
