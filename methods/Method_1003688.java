@Override public void transmit(HttpResponseStatus status,Path file){
  String sizeString=responseHeaders.getAsString(HttpHeaderConstants.CONTENT_LENGTH);
  long size=sizeString == null ? 0 : Long.parseLong(sizeString);
  boolean compress=!responseHeaders.contains(HttpHeaderConstants.CONTENT_ENCODING,HttpHeaderConstants.IDENTITY,true);
  if (!isSsl && !compress && file.getFileSystem().equals(FileSystems.getDefault())) {
    FileChannel fileChannel;
    try {
      fileChannel=FileChannel.open(file,OPEN_OPTIONS);
    }
 catch (    IOException e) {
      throw new UncheckedIOException(e);
    }
    FileRegion defaultFileRegion=new DefaultFileRegion(fileChannel,0,size);
    transmit(status,defaultFileRegion,true);
  }
 else {
    Blocking.get(() -> Files.newByteChannel(file)).then(fileChannel -> transmit(status,new HttpChunkedInput(new ChunkedNioStream(fileChannel)),false));
  }
}
