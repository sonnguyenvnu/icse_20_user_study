private static boolean isNotAResourceFile(@NonNull String fileName){
  for (  String name : NON_RESOURCES_FILENAMES) {
    if (name.equalsIgnoreCase(fileName)) {
      return true;
    }
  }
  return false;
}
