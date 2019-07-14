private static int buildPrimaryAndEmbeddedTrackGroupInfos(List<AdaptationSet> adaptationSets,int[][] groupedAdaptationSetIndices,int primaryGroupCount,boolean[] primaryGroupHasEventMessageTrackFlags,boolean[] primaryGroupHasCea608TrackFlags,TrackGroup[] trackGroups,TrackGroupInfo[] trackGroupInfos){
  int trackGroupCount=0;
  for (int i=0; i < primaryGroupCount; i++) {
    int[] adaptationSetIndices=groupedAdaptationSetIndices[i];
    List<Representation> representations=new ArrayList<>();
    for (    int adaptationSetIndex : adaptationSetIndices) {
      representations.addAll(adaptationSets.get(adaptationSetIndex).representations);
    }
    Format[] formats=new Format[representations.size()];
    for (int j=0; j < formats.length; j++) {
      formats[j]=representations.get(j).format;
    }
    AdaptationSet firstAdaptationSet=adaptationSets.get(adaptationSetIndices[0]);
    int primaryTrackGroupIndex=trackGroupCount++;
    int eventMessageTrackGroupIndex=primaryGroupHasEventMessageTrackFlags[i] ? trackGroupCount++ : C.INDEX_UNSET;
    int cea608TrackGroupIndex=primaryGroupHasCea608TrackFlags[i] ? trackGroupCount++ : C.INDEX_UNSET;
    trackGroups[primaryTrackGroupIndex]=new TrackGroup(formats);
    trackGroupInfos[primaryTrackGroupIndex]=TrackGroupInfo.primaryTrack(firstAdaptationSet.type,adaptationSetIndices,primaryTrackGroupIndex,eventMessageTrackGroupIndex,cea608TrackGroupIndex);
    if (eventMessageTrackGroupIndex != C.INDEX_UNSET) {
      Format format=Format.createSampleFormat(firstAdaptationSet.id + ":emsg",MimeTypes.APPLICATION_EMSG,null,Format.NO_VALUE,null);
      trackGroups[eventMessageTrackGroupIndex]=new TrackGroup(format);
      trackGroupInfos[eventMessageTrackGroupIndex]=TrackGroupInfo.embeddedEmsgTrack(adaptationSetIndices,primaryTrackGroupIndex);
    }
    if (cea608TrackGroupIndex != C.INDEX_UNSET) {
      Format format=Format.createTextSampleFormat(firstAdaptationSet.id + ":cea608",MimeTypes.APPLICATION_CEA608,0,null);
      trackGroups[cea608TrackGroupIndex]=new TrackGroup(format);
      trackGroupInfos[cea608TrackGroupIndex]=TrackGroupInfo.embeddedCea608Track(adaptationSetIndices,primaryTrackGroupIndex);
    }
  }
  return trackGroupCount;
}
