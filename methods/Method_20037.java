@Nullable public static Bitmap getBitmap(ByteBuffer data,FrameMetadata metadata){
  data.rewind();
  byte[] imageInBuffer=new byte[data.limit()];
  data.get(imageInBuffer,0,imageInBuffer.length);
  try {
    YuvImage image=new YuvImage(imageInBuffer,ImageFormat.NV21,metadata.getWidth(),metadata.getHeight(),null);
    if (image != null) {
      ByteArrayOutputStream stream=new ByteArrayOutputStream();
      image.compressToJpeg(new Rect(0,0,metadata.getWidth(),metadata.getHeight()),80,stream);
      Bitmap bmp=BitmapFactory.decodeByteArray(stream.toByteArray(),0,stream.size());
      stream.close();
      return rotateBitmap(bmp,metadata.getRotation(),metadata.getCameraFacing());
    }
  }
 catch (  Exception e) {
    Log.e("VisionProcessorBase","Error: " + e.getMessage());
  }
  return null;
}
