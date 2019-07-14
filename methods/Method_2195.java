@DoNotStrip private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd){
  return BitmapFactory.decodeFileDescriptor(fd);
}
