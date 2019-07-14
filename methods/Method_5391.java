/** 
 * Builds tracks that are exposed by this  {@link HlsSampleStreamWrapper} instance, as well asinternal data-structures required for operation. <p>Tracks in HLS are complicated. A HLS master playlist contains a number of "variants". Each variant stream typically contains muxed video, audio and (possibly) additional audio, metadata and caption tracks. We wish to allow the user to select between an adaptive track that spans all variants, as well as each individual variant. If multiple audio tracks are present within each variant then we wish to allow the user to select between those also. <p>To do this, tracks are constructed as follows. The  {@link HlsChunkSource} exposes (N+1)tracks, where N is the number of variants defined in the HLS master playlist. These consist of one adaptive track defined to span all variants and a track for each individual variant. The adaptive track is initially selected. The extractor is then prepared to discover the tracks inside of each variant stream. The two sets of tracks are then combined by this method to create a third set, which is the set exposed by this  {@link HlsSampleStreamWrapper}: <ul> <li>The extractor tracks are inspected to infer a "primary" track type. If a video track is present then it is always the primary type. If not, audio is the primary type if present. Else text is the primary type if present. Else there is no primary type. <li>If there is exactly one extractor track of the primary type, it's expanded into (N+1) exposed tracks, all of which correspond to the primary extractor track and each of which corresponds to a different chunk source track. Selecting one of these tracks has the effect of switching the selected track on the chunk source. <li>All other extractor tracks are exposed directly. Selecting one of these tracks has the effect of selecting an extractor track, leaving the selected track on the chunk source unchanged. </ul>
 */
private void buildTracksFromSampleStreams(){
  int primaryExtractorTrackType=C.TRACK_TYPE_NONE;
  int primaryExtractorTrackIndex=C.INDEX_UNSET;
  int extractorTrackCount=sampleQueues.length;
  for (int i=0; i < extractorTrackCount; i++) {
    String sampleMimeType=sampleQueues[i].getUpstreamFormat().sampleMimeType;
    int trackType;
    if (MimeTypes.isVideo(sampleMimeType)) {
      trackType=C.TRACK_TYPE_VIDEO;
    }
 else     if (MimeTypes.isAudio(sampleMimeType)) {
      trackType=C.TRACK_TYPE_AUDIO;
    }
 else     if (MimeTypes.isText(sampleMimeType)) {
      trackType=C.TRACK_TYPE_TEXT;
    }
 else {
      trackType=C.TRACK_TYPE_NONE;
    }
    if (getTrackTypeScore(trackType) > getTrackTypeScore(primaryExtractorTrackType)) {
      primaryExtractorTrackType=trackType;
      primaryExtractorTrackIndex=i;
    }
 else     if (trackType == primaryExtractorTrackType && primaryExtractorTrackIndex != C.INDEX_UNSET) {
      primaryExtractorTrackIndex=C.INDEX_UNSET;
    }
  }
  TrackGroup chunkSourceTrackGroup=chunkSource.getTrackGroup();
  int chunkSourceTrackCount=chunkSourceTrackGroup.length;
  primaryTrackGroupIndex=C.INDEX_UNSET;
  trackGroupToSampleQueueIndex=new int[extractorTrackCount];
  for (int i=0; i < extractorTrackCount; i++) {
    trackGroupToSampleQueueIndex[i]=i;
  }
  TrackGroup[] trackGroups=new TrackGroup[extractorTrackCount];
  for (int i=0; i < extractorTrackCount; i++) {
    Format sampleFormat=sampleQueues[i].getUpstreamFormat();
    if (i == primaryExtractorTrackIndex) {
      Format[] formats=new Format[chunkSourceTrackCount];
      if (chunkSourceTrackCount == 1) {
        formats[0]=sampleFormat.copyWithManifestFormatInfo(chunkSourceTrackGroup.getFormat(0));
      }
 else {
        for (int j=0; j < chunkSourceTrackCount; j++) {
          formats[j]=deriveFormat(chunkSourceTrackGroup.getFormat(j),sampleFormat,true);
        }
      }
      trackGroups[i]=new TrackGroup(formats);
      primaryTrackGroupIndex=i;
    }
 else {
      Format trackFormat=primaryExtractorTrackType == C.TRACK_TYPE_VIDEO && MimeTypes.isAudio(sampleFormat.sampleMimeType) ? muxedAudioFormat : null;
      trackGroups[i]=new TrackGroup(deriveFormat(trackFormat,sampleFormat,false));
    }
  }
  this.trackGroups=new TrackGroupArray(trackGroups);
  Assertions.checkState(optionalTrackGroups == null);
  optionalTrackGroups=TrackGroupArray.EMPTY;
}
