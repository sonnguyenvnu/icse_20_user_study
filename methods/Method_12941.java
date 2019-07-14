/** 
 * Determine the RFC 3066 compliant language tag, as used for the HTTP "Accept-Language" header.
 * @param locale the Locale to transform to a language tag
 * @return the RFC 3066 compliant language tag as {@code String}
 */
public static String toLanguageTag(Locale locale){
  return locale.getLanguage() + (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
}
