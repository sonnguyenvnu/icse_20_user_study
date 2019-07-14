/** 
 * Adds parent only rules for classes which must be loaded on the parent loader.
 */
public void addParentOnlyRules(final String... packages){
  parentOnlyRules=ArraysUtil.join(parentOnlyRules,packages);
}
