@Override public Reader read(){
  try {
    return Channels.newReader(FileChannel.open(file.toPath(),StandardOpenOption.READ),encoding.newDecoder(),4096);
  }
 catch (  IOException e) {
    throw new FlywayException("Unable to load filesystem resource: " + file.getPath() + " (encoding: " + encoding + ")",e);
  }
}
