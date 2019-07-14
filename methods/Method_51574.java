/** 
 * Does any Rule for the given Language use multi-file analysis?
 * @param language The Language.
 * @return {@code true} if a Rule for the Language uses multi file analysis,{@code false} otherwise.
 * @deprecated See {@link Rule#isMultifile()}
 */
@Deprecated public boolean usesMultifile(Language language){
  for (  RuleSet ruleSet : ruleSets) {
    if (ruleSet.usesMultifile(language)) {
      return true;
    }
  }
  return false;
}
