@Override public String getFilename(){
  return isBucket() ? this.bucketName : this.objectKey;
}
