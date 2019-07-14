/** 
 * Construct the link text to include in the compiler error message. Returns null if there is no link.
 */
@Nullable private static String linkTextForDiagnostic(String linkUrl){
  return isNullOrEmpty(linkUrl) ? null : "  (see " + linkUrl + ")";
}
