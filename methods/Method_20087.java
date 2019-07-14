private void processImage(ByteBuffer data,final FrameMetadata frameMetadata,final GraphicOverlay graphicOverlay){
  FirebaseVisionImageMetadata metadata=new FirebaseVisionImageMetadata.Builder().setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21).setWidth(frameMetadata.getWidth()).setHeight(frameMetadata.getHeight()).setRotation(frameMetadata.getRotation()).build();
  Bitmap bitmap=BitmapUtils.getBitmap(data,frameMetadata);
  detectInVisionImage(bitmap,FirebaseVisionImage.fromByteBuffer(data,metadata),frameMetadata,graphicOverlay);
}
