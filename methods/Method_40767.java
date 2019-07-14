@Nullable public static String readFile(@NotNull String path){
  byte[] content=getBytesFromFile(path);
  if (content == null) {
    return null;
  }
 else {
    return new String(content,UTF_8);
  }
}
