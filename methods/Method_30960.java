public static int indexOfExtensionSeparator(@NonNull String path){
  int lastSeparatorIndex=indexOfLastSeparator(path);
  int lastExtensionSeparatorIndex=path.lastIndexOf(EXTENSION_SEPARATOR);
  return lastSeparatorIndex > lastExtensionSeparatorIndex ? -1 : lastExtensionSeparatorIndex;
}
