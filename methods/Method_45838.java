public static int maskedCrc32c(byte[] data,int offset,int length){
  Crc32C crc32c=new Crc32C();
  crc32c.update(data,offset,length);
  return crc32c.getMaskedValue();
}
