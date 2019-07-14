private void storeYuvData(byte[] yuv,int width,int height){
  if (handler != null)   freeYuvData();
  handler=jniStoreYuvData(yuv,width,height);
}
