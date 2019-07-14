/** 
 * Returns `true` if the "app_banner" query param is set in the intent uri.
 */
public static boolean appBannerIsSet(final @NonNull Intent intent){
  final Uri uri=uri(intent);
  if (uri == null) {
    return false;
  }
 else {
    final String queryParam=uri.getQueryParameter("app_banner");
    return "1".equals(queryParam);
  }
}
