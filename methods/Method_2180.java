private static @Nullable byte[] getWebpHeader(InputStream inputStream,BitmapFactory.Options opts){
  inputStream.mark(HEADER_SIZE);
  byte[] header;
  if (opts != null && opts.inTempStorage != null && opts.inTempStorage.length >= HEADER_SIZE) {
    header=opts.inTempStorage;
  }
 else {
    header=new byte[HEADER_SIZE];
  }
  try {
    inputStream.read(header,0,HEADER_SIZE);
    inputStream.reset();
  }
 catch (  IOException exp) {
    return null;
  }
  return header;
}
