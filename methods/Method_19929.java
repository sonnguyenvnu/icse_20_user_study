@Nullable @Override public String getType(@NonNull Uri uri){
  final File file=uriToFile(uri);
  if (!isFileInRoot(file)) {
    throw new SecurityException("File is not in root: " + file);
  }
  return getMimeType(file);
}
