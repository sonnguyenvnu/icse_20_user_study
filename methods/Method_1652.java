private int getLength(ImageRequest imageRequest){
  AssetFileDescriptor fd=null;
  try {
    fd=mAssetManager.openFd(getAssetName(imageRequest));
    return (int)fd.getLength();
  }
 catch (  IOException e) {
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
