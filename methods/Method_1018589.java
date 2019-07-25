public static byte[] convert(int[] in,GaloisField f){
  if (f.size() >= 256) {
    byte[] res=new byte[in.length];
    for (int i=0; i < in.length; i++)     res[i]=(byte)in[i];
    return res;
  }
  if (f.size() == 16) {
    byte[] res=new byte[in.length / 2];
    for (int i=0; i < res.length; i++)     res[i]=(byte)(in[2 * i] | (in[2 * i + 1] << 4));
    return res;
  }
  throw new IllegalStateException("Unimplemented GaloisField size conversion");
}
