private static StaticBuffer getPrefixed(int prefix){
  assert prefix < (1 << PREFIX_BIT_LEN) && prefix >= 0;
  byte[] arr=new byte[1];
  arr[0]=(byte)(prefix << (Byte.SIZE - PREFIX_BIT_LEN));
  return new StaticArrayBuffer(arr);
}
