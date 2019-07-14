private static @Nullable Representation getFirstRepresentation(Period period,int type){
  int index=period.getAdaptationSetIndex(type);
  if (index == C.INDEX_UNSET) {
    return null;
  }
  List<Representation> representations=period.adaptationSets.get(index).representations;
  return representations.isEmpty() ? null : representations.get(0);
}
