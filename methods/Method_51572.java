/** 
 * Check if the rules that apply to a source of the given language use DFA.
 * @param language the language of a source
 * @return true if any rule in the RuleSet needs the DFA layer
 * @deprecated See {@link Rule#isDfa()}
 */
@Deprecated public boolean usesDFA(Language language){
  for (  RuleSet ruleSet : ruleSets) {
    if (ruleSet.usesDFA(language)) {
      return true;
    }
  }
  return false;
}
