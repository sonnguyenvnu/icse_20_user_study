private static VirtualFile validated(final VirtualFile file){
  if (file == null || !file.isValid()) {
    return null;
  }
  return file;
}
