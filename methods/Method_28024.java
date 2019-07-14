@Nullable @Override public List<RepoFile> getCachedFiles(@NonNull String url,@NonNull String ref){
  return pathsModel.getPaths(url,ref);
}
