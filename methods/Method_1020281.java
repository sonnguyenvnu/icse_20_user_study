public static int exists(String fileName) throws IOException {
  java.io.File file=getFile(fileName);
  if (file.exists()) {
    return 1;
  }
 else {
    return -1;
  }
}
