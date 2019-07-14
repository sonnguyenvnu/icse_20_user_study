/** 
 * Updates the stored track metadata to reflect the contents of the specified moov atom.
 */
private void processMoovAtom(ContainerAtom moov) throws ParserException {
  int firstVideoTrackIndex=C.INDEX_UNSET;
  long durationUs=C.TIME_UNSET;
  List<Mp4Track> tracks=new ArrayList<>();
  Metadata udtaMetadata=null;
  GaplessInfoHolder gaplessInfoHolder=new GaplessInfoHolder();
  Atom.LeafAtom udta=moov.getLeafAtomOfType(Atom.TYPE_udta);
  if (udta != null) {
    udtaMetadata=AtomParsers.parseUdta(udta,isQuickTime);
    if (udtaMetadata != null) {
      gaplessInfoHolder.setFromMetadata(udtaMetadata);
    }
  }
  Metadata mdtaMetadata=null;
  Atom.ContainerAtom meta=moov.getContainerAtomOfType(Atom.TYPE_meta);
  if (meta != null) {
    mdtaMetadata=AtomParsers.parseMdtaFromMeta(meta);
  }
  boolean ignoreEditLists=(flags & FLAG_WORKAROUND_IGNORE_EDIT_LISTS) != 0;
  ArrayList<TrackSampleTable> trackSampleTables=getTrackSampleTables(moov,gaplessInfoHolder,ignoreEditLists);
  int trackCount=trackSampleTables.size();
  for (int i=0; i < trackCount; i++) {
    TrackSampleTable trackSampleTable=trackSampleTables.get(i);
    Track track=trackSampleTable.track;
    Mp4Track mp4Track=new Mp4Track(track,trackSampleTable,extractorOutput.track(i,track.type));
    int maxInputSize=trackSampleTable.maximumSize + 3 * 10;
    Format format=track.format.copyWithMaxInputSize(maxInputSize);
    format=MetadataUtil.getFormatWithMetadata(track.type,format,udtaMetadata,mdtaMetadata,gaplessInfoHolder);
    mp4Track.trackOutput.format(format);
    durationUs=Math.max(durationUs,track.durationUs != C.TIME_UNSET ? track.durationUs : trackSampleTable.durationUs);
    if (track.type == C.TRACK_TYPE_VIDEO && firstVideoTrackIndex == C.INDEX_UNSET) {
      firstVideoTrackIndex=tracks.size();
    }
    tracks.add(mp4Track);
  }
  this.firstVideoTrackIndex=firstVideoTrackIndex;
  this.durationUs=durationUs;
  this.tracks=tracks.toArray(new Mp4Track[0]);
  accumulatedSampleSizes=calculateAccumulatedSampleSizes(this.tracks);
  extractorOutput.endTracks();
  extractorOutput.seekMap(this);
}
