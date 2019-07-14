@Nullable public static String readFile(@NotNull String path){
  try {
    byte[] encoded=Files.readAllBytes(Paths.get(path));
    return Charset.forName("UTF-8").decode(ByteBuffer.wrap(encoded)).toString();
  }
 catch (  IOException e) {
    return null;
  }
}
