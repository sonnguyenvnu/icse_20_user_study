public static byte[] xor(byte data[],byte key[]){
  final byte[] result=new byte[data.length];
  int pos=0;
  for (int i=0; i < result.length; i++) {
    result[i]=(byte)(data[i] ^ key[pos++]);
    if (pos == key.length) {
      pos=0;
    }
  }
  return result;
}
