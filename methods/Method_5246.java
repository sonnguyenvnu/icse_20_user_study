private static ArrayList<AdaptationSet> copyAdaptationSets(List<AdaptationSet> adaptationSets,LinkedList<StreamKey> keys){
  StreamKey key=keys.poll();
  int periodIndex=key.periodIndex;
  ArrayList<AdaptationSet> copyAdaptationSets=new ArrayList<>();
  do {
    int adaptationSetIndex=key.groupIndex;
    AdaptationSet adaptationSet=adaptationSets.get(adaptationSetIndex);
    List<Representation> representations=adaptationSet.representations;
    ArrayList<Representation> copyRepresentations=new ArrayList<>();
    do {
      Representation representation=representations.get(key.trackIndex);
      copyRepresentations.add(representation);
      key=keys.poll();
    }
 while (key.periodIndex == periodIndex && key.groupIndex == adaptationSetIndex);
    copyAdaptationSets.add(new AdaptationSet(adaptationSet.id,adaptationSet.type,copyRepresentations,adaptationSet.accessibilityDescriptors,adaptationSet.supplementalProperties));
  }
 while (key.periodIndex == periodIndex);
  keys.addFirst(key);
  return copyAdaptationSets;
}
