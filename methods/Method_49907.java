/** 
 * Convert obsolete language codes, including Hebrew/Indonesian/Yiddish, to new standard.
 */
private static String convertObsoleteLanguageCodeToNew(String langCode){
  if (langCode == null) {
    return null;
  }
  if ("iw".equals(langCode)) {
    return "he";
  }
 else   if ("in".equals(langCode)) {
    return "id";
  }
 else   if ("ji".equals(langCode)) {
    return "yi";
  }
  return langCode;
}
