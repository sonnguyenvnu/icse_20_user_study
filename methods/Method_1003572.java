@Nullable public Uri location(@Nullable String location){
  if (TextUtils.isEmpty(location))   return null;
  if (URLUtil.isNetworkUrl(location)) {
    return newBuilder(location).build();
  }
  URI newUri=URI.create(location);
  if (location.startsWith("/")) {
    return builder().setPath(newUri.getPath()).setQuery(newUri.getQuery()).setFragment(newUri.getFragment()).build();
  }
 else   if (location.contains("../")) {
    List<String> oldPathList=convertPath(getPath());
    List<String> newPathList=convertPath(newUri.getPath());
    int start=newPathList.lastIndexOf("..");
    newPathList=newPathList.subList(start + 1,newPathList.size());
    if (!oldPathList.isEmpty()) {
      oldPathList=oldPathList.subList(0,oldPathList.size() - start - 2);
      oldPathList.addAll(newPathList);
      String path=TextUtils.join("/",oldPathList);
      return builder().setPath(path).setQuery(newUri.getQuery()).setFragment(newUri.getFragment()).build();
    }
    String path=TextUtils.join("/",newPathList);
    return builder().setPath(path).setQuery(newUri.getQuery()).setFragment(newUri.getFragment()).build();
  }
 else {
    List<String> oldPathList=convertPath(getPath());
    oldPathList.addAll(convertPath(newUri.getPath()));
    String path=TextUtils.join("/",oldPathList);
    return builder().setPath(path).setQuery(newUri.getQuery()).setFragment(newUri.getFragment()).build();
  }
}
