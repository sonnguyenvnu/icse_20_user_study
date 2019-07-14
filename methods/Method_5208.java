private static boolean hasEventMessageTrack(List<AdaptationSet> adaptationSets,int[] adaptationSetIndices){
  for (  int i : adaptationSetIndices) {
    List<Representation> representations=adaptationSets.get(i).representations;
    for (int j=0; j < representations.size(); j++) {
      Representation representation=representations.get(j);
      if (!representation.inbandEventStreams.isEmpty()) {
        return true;
      }
    }
  }
  return false;
}
