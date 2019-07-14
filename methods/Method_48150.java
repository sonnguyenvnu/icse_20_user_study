/** 
 * Get information about all registered  {@link IndexProvider}s.
 * @return
 */
public Map<String,IndexInformation> getIndexInformation(){
  ImmutableMap.Builder<String,IndexInformation> copy=ImmutableMap.builder();
  copy.putAll(indexes);
  return copy.build();
}
