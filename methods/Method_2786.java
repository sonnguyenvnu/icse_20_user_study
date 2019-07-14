public double nextDouble(){
  double result=ByteUtil.bytesHighFirstToDouble(bytes,offset);
  offset+=8;
  return result;
}
