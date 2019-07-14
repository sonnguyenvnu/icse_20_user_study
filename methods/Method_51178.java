/** 
 * A utility method to find the Languages which have Rule support.
 * @return A List of Languages with Rule support.
 * @deprecated This method will be removed with PMD 7.0.0. Use {@link #getLanguages()} instead.
 */
@Deprecated public static List<Language> findWithRuleSupport(){
  return new ArrayList<>(getLanguages());
}
