@JsonCreator public static Features create(final Set<Feature> features){
  final SortedSet<Feature> featureTreeSet=new TreeSet<>(features);
  return new AutoValue_Features(featureTreeSet);
}
