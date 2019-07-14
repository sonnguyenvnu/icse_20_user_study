@Override public @Nullable BinaryResource getResource(String resourceId,Object debugInfo){
  final File file=getContentFileFor(resourceId);
  if (file.exists()) {
    file.setLastModified(mClock.now());
    return FileBinaryResource.createOrNull(file);
  }
  return null;
}
