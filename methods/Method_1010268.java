@NotNull public static String relativize(@NotNull String fullPath,@NotNull IFile contentHome){
  return relativize(fullPath,contentHome.getPath());
}
