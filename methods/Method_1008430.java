/** 
 * Parse the given locale as  {@code language},  {@code language-country} or{@code language-country-variant}. Either underscores or hyphens may be used as separators, but consistently, ie. you may not use an hyphen to separate the language from the country and an underscore to separate the country from the variant.
 * @throws IllegalArgumentException if there are too many parts in the locale string
 * @throws IllegalArgumentException if the language or country is not recognized
 */
public static Locale parse(String localeStr){
  boolean useUnderscoreAsSeparator=false;
  for (int i=0; i < localeStr.length(); ++i) {
    final char c=localeStr.charAt(i);
    if (c == '-') {
      break;
    }
 else     if (c == '_') {
      useUnderscoreAsSeparator=true;
      break;
    }
  }
  final String[] parts;
  if (useUnderscoreAsSeparator) {
    parts=localeStr.split("_",-1);
  }
 else {
    parts=localeStr.split("-",-1);
  }
  final Locale locale=parseParts(parts);
  try {
    locale.getISO3Language();
  }
 catch (  MissingResourceException e) {
    throw new IllegalArgumentException("Unknown language: " + parts[0],e);
  }
  try {
    locale.getISO3Country();
  }
 catch (  MissingResourceException e) {
    throw new IllegalArgumentException("Unknown country: " + parts[1],e);
  }
  return locale;
}
