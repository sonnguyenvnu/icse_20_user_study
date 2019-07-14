private Strength strengthOf(Feature feature){
  for (  Strength strength : Strength.values()) {
    if (feature.name().startsWith(strength.name())) {
      return strength;
    }
  }
  throw new IllegalStateException("No strength for " + feature);
}
