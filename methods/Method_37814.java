public static void putInt(final byte[] b,final int off,final int val){
  b[off + 3]=(byte)(val);
  b[off + 2]=(byte)(val >>> 8);
  b[off + 1]=(byte)(val >>> 16);
  b[off]=(byte)(val >>> 24);
}
