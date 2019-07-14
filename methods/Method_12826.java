private static boolean isOfNonResourcesExtensions(@NonNull String extension,boolean allowClassFiles){
  for (  String ext : NON_RESOURCES_EXTENSIONS) {
    if (ext.equalsIgnoreCase(extension)) {
      return true;
    }
  }
  return !allowClassFiles && SdkConstants.EXT_CLASS.equals(extension);
}
