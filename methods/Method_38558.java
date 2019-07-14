@Override public byte[] getBytes(){
  try {
    return FileUtil.readBytes(file);
  }
 catch (  IOException ioex) {
    throw new HttpException(ioex);
  }
}
