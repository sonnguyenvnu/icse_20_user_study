/** 
 * Combine this feature set with another.
 * @param other Other set to combine with.
 * @return a new feature set.
 */
public FeatureSet combine(final FeatureSet other){
  final Set<Feature> enabled=new HashSet<>(this.enabled);
  enabled.addAll(other.enabled);
  final Set<Feature> disabled=new HashSet<>(this.disabled);
  disabled.addAll(other.disabled);
  return new FeatureSet(enabled,disabled);
}
