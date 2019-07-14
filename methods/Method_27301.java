public static boolean isMarkdown(@Nullable String name){
  if (InputHelper.isEmpty(name))   return false;
  name=name.toLowerCase();
  for (  String value : MARKDOWN_EXTENSIONS) {
    String extension=MimeTypeMap.getFileExtensionFromUrl(name);
    if ((extension != null && value.replace(".","").equals(extension)) || name.equalsIgnoreCase("README") || name.endsWith(value))     return true;
  }
  return false;
}
