/** 
 * We want to only request given permissions If we do not call this method, the library will find all needed permissions to ask from manifest
 * @see android.Manifest.permission
 */
public RuntimePermission request(@Nullable final List<String> permissions){
  if (permissions != null) {
    permissionsToRequest.clear();
    permissionsToRequest.addAll(permissions);
  }
  return this;
}
