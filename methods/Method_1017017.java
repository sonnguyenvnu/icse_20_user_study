@JsonValue public List<String> value(){
  final List<String> features=new ArrayList<>();
  for (  final Feature feature : enabled) {
    features.add(feature.id());
  }
  for (  final Feature feature : disabled) {
    features.add("-" + feature.id());
  }
  return features;
}
