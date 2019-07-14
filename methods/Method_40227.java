@Nullable public static byte[] getBytesFromFile(@NotNull String filename){
  try {
    return FileUtils.readFileToByteArray(new File(filename));
  }
 catch (  Exception e) {
    return null;
  }
}
