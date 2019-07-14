/** 
 * Returns the next chunk to load. <p>If a chunk is available then  {@link HlsChunkHolder#chunk} is set. If the end of the streamhas been reached then  {@link HlsChunkHolder#endOfStream} is set. If a chunk is not availablebut the end of the stream has not been reached,  {@link HlsChunkHolder#playlist} is set tocontain the  {@link HlsUrl} that refers to the playlist that needs refreshing.
 * @param playbackPositionUs The current playback position relative to the period start inmicroseconds. If playback of the period to which this chunk source belongs has not yet started, the value will be the starting position in the period minus the duration of any media in previous periods still to be played.
 * @param loadPositionUs The current load position relative to the period start in microseconds.
 * @param queue The queue of buffered {@link HlsMediaChunk}s.
 * @param out A holder to populate.
 */
public void getNextChunk(long playbackPositionUs,long loadPositionUs,List<HlsMediaChunk> queue,HlsChunkHolder out){
  HlsMediaChunk previous=queue.isEmpty() ? null : queue.get(queue.size() - 1);
  int oldVariantIndex=previous == null ? C.INDEX_UNSET : trackGroup.indexOf(previous.trackFormat);
  long bufferedDurationUs=loadPositionUs - playbackPositionUs;
  long timeToLiveEdgeUs=resolveTimeToLiveEdgeUs(playbackPositionUs);
  if (previous != null && !independentSegments) {
    long subtractedDurationUs=previous.getDurationUs();
    bufferedDurationUs=Math.max(0,bufferedDurationUs - subtractedDurationUs);
    if (timeToLiveEdgeUs != C.TIME_UNSET) {
      timeToLiveEdgeUs=Math.max(0,timeToLiveEdgeUs - subtractedDurationUs);
    }
  }
  MediaChunkIterator[] mediaChunkIterators=createMediaChunkIterators(previous,loadPositionUs);
  trackSelection.updateSelectedTrack(playbackPositionUs,bufferedDurationUs,timeToLiveEdgeUs,queue,mediaChunkIterators);
  int selectedVariantIndex=trackSelection.getSelectedIndexInTrackGroup();
  boolean switchingVariant=oldVariantIndex != selectedVariantIndex;
  HlsUrl selectedUrl=variants[selectedVariantIndex];
  if (!playlistTracker.isSnapshotValid(selectedUrl)) {
    out.playlist=selectedUrl;
    seenExpectedPlaylistError&=expectedPlaylistUrl == selectedUrl;
    expectedPlaylistUrl=selectedUrl;
    return;
  }
  HlsMediaPlaylist mediaPlaylist=playlistTracker.getPlaylistSnapshot(selectedUrl,true);
  independentSegments=mediaPlaylist.hasIndependentSegments;
  updateLiveEdgeTimeUs(mediaPlaylist);
  long startOfPlaylistInPeriodUs=mediaPlaylist.startTimeUs - playlistTracker.getInitialStartTimeUs();
  long chunkMediaSequence=getChunkMediaSequence(previous,switchingVariant,mediaPlaylist,startOfPlaylistInPeriodUs,loadPositionUs);
  if (chunkMediaSequence < mediaPlaylist.mediaSequence) {
    if (previous != null && switchingVariant) {
      selectedVariantIndex=oldVariantIndex;
      selectedUrl=variants[selectedVariantIndex];
      mediaPlaylist=playlistTracker.getPlaylistSnapshot(selectedUrl,true);
      startOfPlaylistInPeriodUs=mediaPlaylist.startTimeUs - playlistTracker.getInitialStartTimeUs();
      chunkMediaSequence=previous.getNextChunkIndex();
    }
 else {
      fatalError=new BehindLiveWindowException();
      return;
    }
  }
  int chunkIndex=(int)(chunkMediaSequence - mediaPlaylist.mediaSequence);
  if (chunkIndex >= mediaPlaylist.segments.size()) {
    if (mediaPlaylist.hasEndTag) {
      out.endOfStream=true;
    }
 else {
      out.playlist=selectedUrl;
      seenExpectedPlaylistError&=expectedPlaylistUrl == selectedUrl;
      expectedPlaylistUrl=selectedUrl;
    }
    return;
  }
  seenExpectedPlaylistError=false;
  expectedPlaylistUrl=null;
  HlsMediaPlaylist.Segment segment=mediaPlaylist.segments.get(chunkIndex);
  if (segment.fullSegmentEncryptionKeyUri != null) {
    Uri keyUri=UriUtil.resolveToUri(mediaPlaylist.baseUri,segment.fullSegmentEncryptionKeyUri);
    if (!keyUri.equals(encryptionKeyUri)) {
      out.chunk=newEncryptionKeyChunk(keyUri,segment.encryptionIV,selectedVariantIndex,trackSelection.getSelectionReason(),trackSelection.getSelectionData());
      return;
    }
    if (!Util.areEqual(segment.encryptionIV,encryptionIvString)) {
      setEncryptionData(keyUri,segment.encryptionIV,encryptionKey);
    }
  }
 else {
    clearEncryptionData();
  }
  DataSpec initDataSpec=null;
  Segment initSegment=segment.initializationSegment;
  if (initSegment != null) {
    Uri initSegmentUri=UriUtil.resolveToUri(mediaPlaylist.baseUri,initSegment.url);
    initDataSpec=new DataSpec(initSegmentUri,initSegment.byterangeOffset,initSegment.byterangeLength,null);
  }
  long segmentStartTimeInPeriodUs=startOfPlaylistInPeriodUs + segment.relativeStartTimeUs;
  int discontinuitySequence=mediaPlaylist.discontinuitySequence + segment.relativeDiscontinuitySequence;
  TimestampAdjuster timestampAdjuster=timestampAdjusterProvider.getAdjuster(discontinuitySequence);
  Uri chunkUri=UriUtil.resolveToUri(mediaPlaylist.baseUri,segment.url);
  DataSpec dataSpec=new DataSpec(chunkUri,segment.byterangeOffset,segment.byterangeLength,null);
  out.chunk=new HlsMediaChunk(extractorFactory,mediaDataSource,dataSpec,initDataSpec,selectedUrl,muxedCaptionFormats,trackSelection.getSelectionReason(),trackSelection.getSelectionData(),segmentStartTimeInPeriodUs,segmentStartTimeInPeriodUs + segment.durationUs,chunkMediaSequence,discontinuitySequence,segment.hasGapTag,isTimestampMaster,timestampAdjuster,previous,segment.drmInitData,encryptionKey,encryptionIv);
}
