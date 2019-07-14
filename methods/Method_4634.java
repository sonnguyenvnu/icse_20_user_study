private void onMoofContainerAtomRead(ContainerAtom moof) throws ParserException {
  parseMoof(moof,trackBundles,flags,extendedTypeScratch);
  DrmInitData drmInitData=sideloadedDrmInitData != null ? null : getDrmInitDataFromAtoms(moof.leafChildren);
  if (drmInitData != null) {
    int trackCount=trackBundles.size();
    for (int i=0; i < trackCount; i++) {
      trackBundles.valueAt(i).updateDrmInitData(drmInitData);
    }
  }
  if (pendingSeekTimeUs != C.TIME_UNSET) {
    int trackCount=trackBundles.size();
    for (int i=0; i < trackCount; i++) {
      trackBundles.valueAt(i).seek(pendingSeekTimeUs);
    }
    pendingSeekTimeUs=C.TIME_UNSET;
  }
}
