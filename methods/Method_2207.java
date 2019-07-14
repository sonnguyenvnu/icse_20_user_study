@Override public synchronized void onImageLoadStatusUpdated(ImagePerfData imagePerfData,@ImageLoadStatus int imageLoadStatus){
  if (imagePerfData == null || imagePerfData.getImageRequest() == null) {
    return;
  }
  if (mImageRequestDebugDataMap.containsKey(imagePerfData.getImageRequest())) {
    mImageRequestDebugDataMap.get(imagePerfData.getImageRequest()).setImagePerfData(imagePerfData);
  }
 else {
    ImageDebugData imageDebugData=new ImageDebugData(imagePerfData.getImageRequest());
    imageDebugData.setImagePerfData(imagePerfData);
    mImageRequestDebugDataMap.put(imagePerfData.getImageRequest(),imageDebugData);
  }
}
