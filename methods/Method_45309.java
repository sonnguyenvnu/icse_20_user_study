public static File directoryOf(final File file){
  checkNotNull(file);
  File parentFile=file.getParentFile();
  if (parentFile == null) {
    return new File(".");
  }
  return parentFile;
}
