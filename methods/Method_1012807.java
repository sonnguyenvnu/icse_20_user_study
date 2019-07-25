/** 
 * Returns the code, except when the alias " {@code loc}" is used. In that case the ISO 639 code of the preferred language in the UMS settings is returned.
 * @param isoCode an {@code ISO 639} code, or "{@code loc}".
 * @return The code.
 */
private static String normalize(String isoCode){
  if (LOCAL_ALIAS.equals(isoCode)) {
    String tag=PMS.getLocale().toLanguageTag();
    int idx=tag.indexOf('-');
    return idx > 0 ? tag.substring(0,idx) : tag;
  }
  return isoCode;
}
