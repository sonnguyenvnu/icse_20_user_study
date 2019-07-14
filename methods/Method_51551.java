/** 
 * Does any Rule for the given Language use Type Resolution?
 * @param language The Language.
 * @return <code>true</code> if a Rule for the Language uses TypeResolution, <code>false</code> otherwise.
 * @deprecated See {@link Rule#isTypeResolution()}
 */
@Deprecated public boolean usesTypeResolution(Language language){
  for (  Rule r : rules) {
    if (r.getLanguage().equals(language) && r.isTypeResolution()) {
      return true;
    }
  }
  return false;
}
