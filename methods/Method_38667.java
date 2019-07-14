/** 
 * Matched current path to queries. If match is found, provided include value may be changed.
 */
public boolean matchPathToQueries(final boolean include){
  return jsonSerializer.rules.apply(path,include);
}
