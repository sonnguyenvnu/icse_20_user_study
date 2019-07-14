private static void copy(URL location,File file) throws IOException {
  try (InputStream in=location.openStream()){
    Files.copy(in,file.toPath(),StandardCopyOption.REPLACE_EXISTING);
  }
 }
