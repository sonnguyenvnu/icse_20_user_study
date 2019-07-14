/** 
 * Build a Firebase Dynamic Link. https://firebase.google.com/docs/dynamic-links/android/create#create-a-dynamic-link-from-parameters
 * @param deepLink the deep link your app will open. This link must be a valid URL and use theHTTP or HTTPS scheme.
 * @param minVersion the {@code versionCode} of the minimum version of your app that can openthe deep link. If the installed app is an older version, the user is taken to the Play store to upgrade the app. Pass 0 if you do not require a minimum version.
 * @return a {@link Uri} representing a properly formed deep link.
 */
@VisibleForTesting public Uri buildDeepLink(@NonNull Uri deepLink,int minVersion){
  String uriPrefix=getString(R.string.dynamic_links_uri_prefix);
  DynamicLink.Builder builder=FirebaseDynamicLinks.getInstance().createDynamicLink().setDomainUriPrefix(uriPrefix).setAndroidParameters(new DynamicLink.AndroidParameters.Builder().setMinimumVersion(minVersion).build()).setLink(deepLink);
  DynamicLink link=builder.buildDynamicLink();
  return link.getUri();
}
