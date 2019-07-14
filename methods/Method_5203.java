private static Pair<TrackGroupArray,TrackGroupInfo[]> buildTrackGroups(List<AdaptationSet> adaptationSets,List<EventStream> eventStreams){
  int[][] groupedAdaptationSetIndices=getGroupedAdaptationSetIndices(adaptationSets);
  int primaryGroupCount=groupedAdaptationSetIndices.length;
  boolean[] primaryGroupHasEventMessageTrackFlags=new boolean[primaryGroupCount];
  boolean[] primaryGroupHasCea608TrackFlags=new boolean[primaryGroupCount];
  int totalEmbeddedTrackGroupCount=identifyEmbeddedTracks(primaryGroupCount,adaptationSets,groupedAdaptationSetIndices,primaryGroupHasEventMessageTrackFlags,primaryGroupHasCea608TrackFlags);
  int totalGroupCount=primaryGroupCount + totalEmbeddedTrackGroupCount + eventStreams.size();
  TrackGroup[] trackGroups=new TrackGroup[totalGroupCount];
  TrackGroupInfo[] trackGroupInfos=new TrackGroupInfo[totalGroupCount];
  int trackGroupCount=buildPrimaryAndEmbeddedTrackGroupInfos(adaptationSets,groupedAdaptationSetIndices,primaryGroupCount,primaryGroupHasEventMessageTrackFlags,primaryGroupHasCea608TrackFlags,trackGroups,trackGroupInfos);
  buildManifestEventTrackGroupInfos(eventStreams,trackGroups,trackGroupInfos,trackGroupCount);
  return Pair.create(new TrackGroupArray(trackGroups),trackGroupInfos);
}
