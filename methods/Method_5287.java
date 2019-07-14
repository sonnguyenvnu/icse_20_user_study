@Override public TrackGroupArray[] getTrackGroupArrays(DashManifest manifest){
  int periodCount=manifest.getPeriodCount();
  TrackGroupArray[] trackGroupArrays=new TrackGroupArray[periodCount];
  for (int periodIndex=0; periodIndex < periodCount; periodIndex++) {
    List<AdaptationSet> adaptationSets=manifest.getPeriod(periodIndex).adaptationSets;
    TrackGroup[] trackGroups=new TrackGroup[adaptationSets.size()];
    for (int i=0; i < trackGroups.length; i++) {
      List<Representation> representations=adaptationSets.get(i).representations;
      Format[] formats=new Format[representations.size()];
      int representationsCount=representations.size();
      for (int j=0; j < representationsCount; j++) {
        formats[j]=representations.get(j).format;
      }
      trackGroups[i]=new TrackGroup(formats);
    }
    trackGroupArrays[periodIndex]=new TrackGroupArray(trackGroups);
  }
  return trackGroupArrays;
}
