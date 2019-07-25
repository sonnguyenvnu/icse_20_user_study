@NotNull public static String relativize(@NotNull String fullPath,@NotNull String contentHomePath){
  contentHomePath=independentAndAbsolute(contentHomePath);
  fullPath=independentAndAbsolute(fullPath);
  if (fullPath.equals(contentHomePath)) {
    return MPSExtentions.DOT;
  }
  return FileUtil.getRelativePath(fullPath,contentHomePath,Path.UNIX_SEPARATOR);
}
