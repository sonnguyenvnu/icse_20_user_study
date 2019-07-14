public static boolean isArchive(@Nullable String name){
  if (InputHelper.isEmpty(name))   return false;
  name=name.toLowerCase();
  for (  String value : ARCHIVE_EXTENSIONS) {
    String extension=MimeTypeMap.getFileExtensionFromUrl(name);
    if ((extension != null && value.replace(".","").equals(extension)) || name.endsWith(value))     return true;
  }
  return false;
}
