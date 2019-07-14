private static String checkFile(File file){
  if (!file.exists()) {
    return "File '" + file.getName() + "' does not exist";
  }
  if (!file.canRead()) {
    return "File '" + file.getName() + "' cannot be read";
  }
  if (file.length() == 0) {
    return "File '" + file.getName() + "' is empty";
  }
  return null;
}
