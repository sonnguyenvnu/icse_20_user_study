private void onMoovContainerAtomRead(ContainerAtom moov) throws ParserException {
  Assertions.checkState(sideloadedTrack == null,"Unexpected moov box.");
  DrmInitData drmInitData=sideloadedDrmInitData != null ? sideloadedDrmInitData : getDrmInitDataFromAtoms(moov.leafChildren);
  ContainerAtom mvex=moov.getContainerAtomOfType(Atom.TYPE_mvex);
  SparseArray<DefaultSampleValues> defaultSampleValuesArray=new SparseArray<>();
  long duration=C.TIME_UNSET;
  int mvexChildrenSize=mvex.leafChildren.size();
  for (int i=0; i < mvexChildrenSize; i++) {
    Atom.LeafAtom atom=mvex.leafChildren.get(i);
    if (atom.type == Atom.TYPE_trex) {
      Pair<Integer,DefaultSampleValues> trexData=parseTrex(atom.data);
      defaultSampleValuesArray.put(trexData.first,trexData.second);
    }
 else     if (atom.type == Atom.TYPE_mehd) {
      duration=parseMehd(atom.data);
    }
  }
  SparseArray<Track> tracks=new SparseArray<>();
  int moovContainerChildrenSize=moov.containerChildren.size();
  for (int i=0; i < moovContainerChildrenSize; i++) {
    Atom.ContainerAtom atom=moov.containerChildren.get(i);
    if (atom.type == Atom.TYPE_trak) {
      Track track=modifyTrack(AtomParsers.parseTrak(atom,moov.getLeafAtomOfType(Atom.TYPE_mvhd),duration,drmInitData,(flags & FLAG_WORKAROUND_IGNORE_EDIT_LISTS) != 0,false));
      if (track != null) {
        tracks.put(track.id,track);
      }
    }
  }
  int trackCount=tracks.size();
  if (trackBundles.size() == 0) {
    for (int i=0; i < trackCount; i++) {
      Track track=tracks.valueAt(i);
      TrackBundle trackBundle=new TrackBundle(extractorOutput.track(i,track.type));
      trackBundle.init(track,getDefaultSampleValues(defaultSampleValuesArray,track.id));
      trackBundles.put(track.id,trackBundle);
      durationUs=Math.max(durationUs,track.durationUs);
    }
    maybeInitExtraTracks();
    extractorOutput.endTracks();
  }
 else {
    Assertions.checkState(trackBundles.size() == trackCount);
    for (int i=0; i < trackCount; i++) {
      Track track=tracks.valueAt(i);
      trackBundles.get(track.id).init(track,getDefaultSampleValues(defaultSampleValuesArray,track.id));
    }
  }
}
