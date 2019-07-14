@Nullable private String getFileExtension(@NonNull File file){
  String extension=null;
  final String filename=file.getName();
  final int index=filename.lastIndexOf('.');
  if (index >= 0) {
    extension=filename.substring(index + 1);
  }
  return extension;
}
