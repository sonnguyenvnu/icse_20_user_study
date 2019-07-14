/** 
 * Returns a normalized RFC 639-2/T code for  {@code language}.
 * @param language A case-insensitive ISO 639 alpha-2 or alpha-3 language code.
 * @return The all-lowercase normalized code, or null if the input was null, or {@code language.toLowerCase()} if the language could not be normalized.
 */
public static @Nullable String normalizeLanguageCode(@Nullable String language){
  try {
    return language == null ? null : new Locale(language).getISO3Language();
  }
 catch (  MissingResourceException e) {
    return toLowerInvariant(language);
  }
}
