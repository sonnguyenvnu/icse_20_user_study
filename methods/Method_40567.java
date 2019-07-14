public static String readFile(File path) throws Exception {
  return new String(getBytesFromFile(path),UTF_8);
}
