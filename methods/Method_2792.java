public float nextFloat(){
  float result=ByteUtil.bytesHighFirstToFloat(bytes,offset);
  offset+=4;
  return result;
}
