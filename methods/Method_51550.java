/** 
 * Does any Rule for the given Language use the DFA layer?
 * @param language The Language.
 * @return <code>true</code> if a Rule for the Language uses the DFA layer,<code>false</code> otherwise.
 * @deprecated See {@link Rule#isDfa()}
 */
@Deprecated public boolean usesDFA(Language language){
  for (  Rule r : rules) {
    if (r.getLanguage().equals(language) && r.isDfa()) {
      return true;
    }
  }
  return false;
}
