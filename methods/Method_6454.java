public float getBufferedProgressFromPosition(final float position,final String fileName){
  if (TextUtils.isEmpty(fileName)) {
    return 0;
  }
  FileLoadOperation loadOperation=loadOperationPaths.get(fileName);
  if (loadOperation != null) {
    return loadOperation.getDownloadedLengthFromOffset(position);
  }
 else {
    return 0.0f;
  }
}
