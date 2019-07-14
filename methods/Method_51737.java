/** 
 * Simply maps PMD languages to rouge languages
 * @param languageTersename
 * @return
 * @see <a href="https://github.com/jneen/rouge/wiki/List-of-supported-languages-and-lexers">List of supported languages</a>
 */
private static String mapLanguageForHighlighting(String languageTersename){
  if (LANGUAGE_HIGHLIGHT_MAPPER.containsKey(languageTersename)) {
    return LANGUAGE_HIGHLIGHT_MAPPER.get(languageTersename);
  }
  return languageTersename;
}
