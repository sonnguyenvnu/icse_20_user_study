private int getLength(ImageRequest imageRequest){
  AssetFileDescriptor fd=null;
  try {
    fd=mResources.openRawResourceFd(getResourceId(imageRequest));
    return (int)fd.getLength();
  }
 catch (  Resources.NotFoundException e) {
    return -1;
  }
 finally {
    try {
      if (fd != null) {
        fd.close();
      }
    }
 catch (    IOException ignored) {
    }
  }
}
