@NotNull private String canonicalize(@NotNull String path){
  path=FileUtil.stripLastSlashes(FileUtil.getUnixPath(path));
  if (path.equals(MPSExtentions.DOT)) {
    path="";
  }
  return path;
}
