/** 
 * Helper method to get a configured parser for the requested language. The parser is configured based on the given  {@link PMDConfiguration}.
 * @param languageVersion the requested language
 * @param configuration the given configuration
 * @return the pre-configured parser
 */
public static Parser parserFor(LanguageVersion languageVersion,PMDConfiguration configuration){
  LanguageVersionHandler languageVersionHandler=languageVersion.getLanguageVersionHandler();
  ParserOptions options=languageVersionHandler.getDefaultParserOptions();
  if (configuration != null) {
    options.setSuppressMarker(configuration.getSuppressMarker());
  }
  return languageVersionHandler.getParser(options);
}
