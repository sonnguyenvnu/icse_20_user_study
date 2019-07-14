@Nullable private static Intent convert(@NonNull Context context,Uri data,boolean showRepoBtn){
  if (data == null)   return null;
  if (InputHelper.isEmpty(data.getHost()) || InputHelper.isEmpty(data.getScheme())) {
    String host=data.getHost();
    if (InputHelper.isEmpty(host))     host=HOST_DEFAULT;
    String scheme=data.getScheme();
    if (InputHelper.isEmpty(scheme))     scheme=PROTOCOL_HTTPS;
    String prefix=scheme + "://" + host;
    String path=data.getPath();
    if (!InputHelper.isEmpty(path)) {
      if (path.charAt(0) == '/') {
        data=Uri.parse(prefix + path);
      }
 else {
        data=Uri.parse(prefix + '/' + path);
      }
    }
 else {
      data=Uri.parse(prefix);
    }
  }
  if (data.getPathSegments() != null && !data.getPathSegments().isEmpty()) {
    if (IGNORED_LIST.contains(data.getPathSegments().get(0)))     return null;
    return getIntentForURI(context,data,showRepoBtn);
  }
  return null;
}
