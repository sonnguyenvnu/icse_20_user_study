public void put(@NotNull Feature feature,@NotNull V value){
  if (MapSequence.fromMap(myFeatureToValue).containsKey(feature)) {
    log("Trying to put already present feature " + feature);
  }
  Feature[] ancestors=feature.getAncestors(myRepo);
  Feature ancestor=Sequence.fromIterable(Sequence.fromArray(ancestors)).findFirst(new IWhereFilter<Feature>(){
    public boolean accept(    Feature a){
      return MapSequence.fromMap(myFeatureToValue).containsKey(a);
    }
  }
);
  if (ancestor != null) {
    log(String.format("Trying to put feature (%s) which is ancestor of already added (%s)",feature,ancestor));
  }
  MapSequence.fromMap(myFeatureToValue).put(feature,value);
  MapSequence.fromMap(myFeatureToAncestors).put(feature,ancestors);
  Sequence.fromIterable(Sequence.fromArray(ancestors)).visitAll(new IVisitor<Feature>(){
    public void visit(    Feature a){
      myFeaturesIsAncestorCounterMap.increment(a);
    }
  }
);
  fireFeatureStateChanged(feature);
}
