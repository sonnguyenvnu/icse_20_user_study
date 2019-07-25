private static void mkdir(String dir) throws IOException {
  File f=new File(dir);
  if (!f.exists()) {
    if (!f.mkdirs()) {
      throw new IOException("Unable to create directory: " + dir);
    }
  }
}
