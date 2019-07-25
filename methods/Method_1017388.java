@NotNull public static String normalize(@NotNull String resource){
  return StringUtils.stripEnd(resource.replace("\\","/").replaceAll("/+","/"),"/");
}
