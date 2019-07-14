/** 
 * Helper function to merge dictionaries of terms to override or provide.
 * @param currentTerms current map of terms to override
 * @param currentTermsIfNew current map of terms to provide if not already there
 * @param newTerms new terms to override
 * @param newTermsIfNew new terms to provide if not already there
 */
private static void mergeSingleTermMaps(Map<String,MonolingualTextValue> currentTerms,Map<String,MonolingualTextValue> currentTermsIfNew,Set<MonolingualTextValue> newTerms,Set<MonolingualTextValue> newTermsIfNew){
  for (  MonolingualTextValue otherLabel : newTerms) {
    String languageCode=otherLabel.getLanguageCode();
    currentTerms.put(languageCode,otherLabel);
    if (currentTermsIfNew.containsKey(languageCode)) {
      currentTermsIfNew.remove(languageCode);
    }
  }
  for (  MonolingualTextValue otherLabel : newTermsIfNew) {
    String languageCode=otherLabel.getLanguageCode();
    if (!currentTermsIfNew.containsKey(languageCode) && !currentTerms.containsKey(languageCode)) {
      currentTermsIfNew.put(languageCode,otherLabel);
    }
  }
}
