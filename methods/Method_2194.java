@DoNotStrip private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd,Rect outPadding,BitmapFactory.Options opts){
  return BitmapFactory.decodeFileDescriptor(fd,outPadding,opts);
}
