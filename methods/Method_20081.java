private void tryReloadAndDetectInImage(){
  try {
    if (imageUri == null) {
      return;
    }
    graphicOverlay.clear();
    Bitmap imageBitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
    Pair<Integer,Integer> targetedSize=getTargetedWidthHeight();
    int targetWidth=targetedSize.first;
    int maxHeight=targetedSize.second;
    float scaleFactor=Math.max((float)imageBitmap.getWidth() / (float)targetWidth,(float)imageBitmap.getHeight() / (float)maxHeight);
    Bitmap resizedBitmap=Bitmap.createScaledBitmap(imageBitmap,(int)(imageBitmap.getWidth() / scaleFactor),(int)(imageBitmap.getHeight() / scaleFactor),true);
    preview.setImageBitmap(resizedBitmap);
    bitmapForDetection=resizedBitmap;
    imageProcessor.process(bitmapForDetection,graphicOverlay);
  }
 catch (  IOException e) {
    Log.e(TAG,"Error retrieving saved image");
  }
}
