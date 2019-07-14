/** 
 * Adds loader-only rules for classes which must be loaded using this loader.
 */
public void addLoaderOnlyRules(final String... packages){
  loaderOnlyRules=ArraysUtil.join(loaderOnlyRules,packages);
}
