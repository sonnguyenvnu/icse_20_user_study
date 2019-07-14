private RandomAccessFile getTmpBucket(){
  try {
    ITempFile tempFile=this.tempFileManager.createTempFile(null);
    return new RandomAccessFile(tempFile.getName(),"rw");
  }
 catch (  Exception e) {
    throw new Error(e);
  }
}
