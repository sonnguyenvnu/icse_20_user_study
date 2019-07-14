@Override public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilities,TrackGroupArray trackGroups,MediaPeriodId periodId,Timeline timeline) throws ExoPlaybackException {
  int[] rendererTrackGroupCounts=new int[rendererCapabilities.length + 1];
  TrackGroup[][] rendererTrackGroups=new TrackGroup[rendererCapabilities.length + 1][];
  int[][][] rendererFormatSupports=new int[rendererCapabilities.length + 1][][];
  for (int i=0; i < rendererTrackGroups.length; i++) {
    rendererTrackGroups[i]=new TrackGroup[trackGroups.length];
    rendererFormatSupports[i]=new int[trackGroups.length][];
  }
  int[] rendererMixedMimeTypeAdaptationSupports=getMixedMimeTypeAdaptationSupports(rendererCapabilities);
  for (int groupIndex=0; groupIndex < trackGroups.length; groupIndex++) {
    TrackGroup group=trackGroups.get(groupIndex);
    int rendererIndex=findRenderer(rendererCapabilities,group);
    int[] rendererFormatSupport=rendererIndex == rendererCapabilities.length ? new int[group.length] : getFormatSupport(rendererCapabilities[rendererIndex],group);
    int rendererTrackGroupCount=rendererTrackGroupCounts[rendererIndex];
    rendererTrackGroups[rendererIndex][rendererTrackGroupCount]=group;
    rendererFormatSupports[rendererIndex][rendererTrackGroupCount]=rendererFormatSupport;
    rendererTrackGroupCounts[rendererIndex]++;
  }
  TrackGroupArray[] rendererTrackGroupArrays=new TrackGroupArray[rendererCapabilities.length];
  int[] rendererTrackTypes=new int[rendererCapabilities.length];
  for (int i=0; i < rendererCapabilities.length; i++) {
    int rendererTrackGroupCount=rendererTrackGroupCounts[i];
    rendererTrackGroupArrays[i]=new TrackGroupArray(Util.nullSafeArrayCopy(rendererTrackGroups[i],rendererTrackGroupCount));
    rendererFormatSupports[i]=Util.nullSafeArrayCopy(rendererFormatSupports[i],rendererTrackGroupCount);
    rendererTrackTypes[i]=rendererCapabilities[i].getTrackType();
  }
  int unmappedTrackGroupCount=rendererTrackGroupCounts[rendererCapabilities.length];
  TrackGroupArray unmappedTrackGroupArray=new TrackGroupArray(Util.nullSafeArrayCopy(rendererTrackGroups[rendererCapabilities.length],unmappedTrackGroupCount));
  MappedTrackInfo mappedTrackInfo=new MappedTrackInfo(rendererTrackTypes,rendererTrackGroupArrays,rendererMixedMimeTypeAdaptationSupports,rendererFormatSupports,unmappedTrackGroupArray);
  Pair<@NullableType RendererConfiguration[],@NullableType TrackSelection[]> result=selectTracks(mappedTrackInfo,rendererFormatSupports,rendererMixedMimeTypeAdaptationSupports);
  return new TrackSelectorResult(result.first,result.second,mappedTrackInfo);
}
