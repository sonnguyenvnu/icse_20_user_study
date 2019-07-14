public static StaticBuffer fillBuffer(int len,byte value){
  byte[] res=new byte[len];
  for (int i=0; i < len; i++)   res[i]=value;
  return StaticArrayBuffer.of(res);
}
