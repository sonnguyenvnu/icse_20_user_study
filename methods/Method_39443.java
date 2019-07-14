/** 
 * Extracts subset of properties that matches given wildcards.
 */
public void extractSubProps(final Map target,final String[] profiles,final String[] wildcardPatterns){
  initialize();
  data.extract(target,profiles,wildcardPatterns,null);
}
