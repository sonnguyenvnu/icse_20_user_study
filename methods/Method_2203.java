@Override public synchronized void trackImageRequest(ImageRequest imageRequest,String requestId){
  ImageDebugData imageDebugData=mImageRequestDebugDataMap.get(imageRequest);
  if (imageDebugData == null) {
    imageDebugData=new ImageDebugData(imageRequest);
    mImageRequestDebugDataMap.put(imageRequest,imageDebugData);
  }
  imageDebugData.addRequestId(requestId);
}
