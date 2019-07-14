@Override protected void processImageBeforeAsyncSave(PImage image){
  if (image.format == AsyncPixelReader.OPENGL_NATIVE) {
    PGL.nativeToJavaARGB(image.pixels,image.pixelWidth,image.pixelHeight);
    image.format=ARGB;
  }
 else   if (image.format == AsyncPixelReader.OPENGL_NATIVE_OPAQUE) {
    PGL.nativeToJavaRGB(image.pixels,image.pixelWidth,image.pixelHeight);
    image.format=RGB;
  }
}
