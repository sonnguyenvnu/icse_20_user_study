@Override public Bitmap decodeFileDescriptor(FileDescriptor fd,Rect outPadding,BitmapFactory.Options opts){
  return hookDecodeFileDescriptor(fd,outPadding,opts);
}
