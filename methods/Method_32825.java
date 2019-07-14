private static void ensureDirectoryExists(File directory){
  if (!directory.isDirectory() && !directory.mkdirs()) {
    throw new RuntimeException("Unable to create directory: " + directory.getAbsolutePath());
  }
}
