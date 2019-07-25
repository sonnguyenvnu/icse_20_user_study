/** 
 * Returns additional (or, user-defined) parameters.
 */
public Map<String,String> additionals(){
  final ImmutableMap.Builder<String,String> builder=ImmutableMap.builder();
  for (  Entry<String,String> e : params.entrySet()) {
    if (!DEFINED_PARAM_KEYS.contains(e.getKey())) {
      builder.put(e.getKey(),e.getValue());
    }
  }
  return builder.build();
}
