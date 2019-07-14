private static @Nullable @FileType String getFileTypefromExtension(String extension){
  if (CONTENT_FILE_EXTENSION.equals(extension)) {
    return FileType.CONTENT;
  }
 else   if (TEMP_FILE_EXTENSION.equals(extension)) {
    return FileType.TEMP;
  }
  return null;
}
