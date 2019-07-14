/** 
 * Takes a variable length of  {@link String} arguments, joins them together to form a single path, thenlooks up a string resource given that path. If the resource cannot be found, returns an empty string.
 */
private @NonNull String stringFromKeyPath(final @NonNull String... keyPathComponents){
  final String keyPath=TextUtils.join("_",keyPathComponents);
  try {
    final int resourceId=this.resources.getIdentifier(keyPath,"string",this.packageName);
    return this.resources.getString(resourceId);
  }
 catch (  final @NonNull Resources.NotFoundException e) {
    return "";
  }
}
