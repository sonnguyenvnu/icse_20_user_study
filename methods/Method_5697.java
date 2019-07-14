/** 
 * Returns whether a  {@link Format} specifies a particular language, or {@code false} if {@code language} is null.
 * @param format The {@link Format}.
 * @param language The language.
 * @return Whether the format specifies the language, or {@code false} if {@code language} isnull.
 */
protected static boolean formatHasLanguage(Format format,@Nullable String language){
  return language != null && TextUtils.equals(language,Util.normalizeLanguageCode(format.language));
}
