@Override public void updateManifest(DashManifest newManifest,int newPeriodIndex){
  try {
    manifest=newManifest;
    periodIndex=newPeriodIndex;
    long periodDurationUs=manifest.getPeriodDurationUs(periodIndex);
    List<Representation> representations=getRepresentations();
    for (int i=0; i < representationHolders.length; i++) {
      Representation representation=representations.get(trackSelection.getIndexInTrackGroup(i));
      representationHolders[i]=representationHolders[i].copyWithNewRepresentation(periodDurationUs,representation);
    }
  }
 catch (  BehindLiveWindowException e) {
    fatalError=e;
  }
}
