/** 
 * Returns a URI for the given resource ID in the given package. Use this method only if you need to specify a package name different to your application's main package.
 * @param packageName a package name (e.g. com.facebook.myapp.plugin)
 * @param resourceId to resource ID to use
 * @return the URI
 */
public static Uri getUriForQualifiedResource(String packageName,int resourceId){
  return new Uri.Builder().scheme(QUALIFIED_RESOURCE_SCHEME).authority(packageName).path(String.valueOf(resourceId)).build();
}
