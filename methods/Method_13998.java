/** 
 * Checks that a language code is valid and returns its preferred version (converting deprecated language codes to their better values).
 * @param lang a Wikimedia language code
 * @return the normalized code, or null if the code is invalid.
 */
public static String normalizeLanguageCode(String lang){
  try {
    if (LanguageCodeStore.ALLOWED_LANGUAGE_CODES.contains(lang)) {
      return WikimediaLanguageCodes.fixLanguageCodeIfDeprecated(lang);
    }
 else {
      return null;
    }
  }
 catch (  IllegalArgumentException e) {
    return null;
  }
}
