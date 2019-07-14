private ArrayList<Representation> getRepresentations(){
  List<AdaptationSet> manifestAdaptationSets=manifest.getPeriod(periodIndex).adaptationSets;
  ArrayList<Representation> representations=new ArrayList<>();
  for (  int adaptationSetIndex : adaptationSetIndices) {
    representations.addAll(manifestAdaptationSets.get(adaptationSetIndex).representations);
  }
  return representations;
}
