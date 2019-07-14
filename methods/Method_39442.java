/** 
 * Extract props of given profiles.
 */
public void extractProps(final Map target,final String... profiles){
  initialize();
  data.extract(target,profiles,null,null);
}
