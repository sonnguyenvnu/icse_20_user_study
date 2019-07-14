private boolean loadBaseAndCheckByFileChannel(String path){
  try {
    FileInputStream fis=new FileInputStream(path);
    FileChannel channel=fis.getChannel();
    int fileSize=(int)channel.size();
    ByteBuffer byteBuffer=ByteBuffer.allocate(fileSize);
    channel.read(byteBuffer);
    byteBuffer.flip();
    byte[] bytes=byteBuffer.array();
    byteBuffer.clear();
    channel.close();
    fis.close();
    int index=0;
    size=ByteUtil.bytesHighFirstToInt(bytes,index);
    index+=4;
    base=new int[size + 65535];
    check=new int[size + 65535];
    for (int i=0; i < size; i++) {
      base[i]=ByteUtil.bytesHighFirstToInt(bytes,index);
      index+=4;
      check[i]=ByteUtil.bytesHighFirstToInt(bytes,index);
      index+=4;
    }
  }
 catch (  Exception e) {
    return false;
  }
  return true;
}
