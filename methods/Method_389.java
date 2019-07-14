private static byte[] allocateBytes(int length){
  byte[] chars=bytesLocal.get();
  if (chars == null) {
    if (length <= 1024 * 64) {
      chars=new byte[1024 * 64];
      bytesLocal.set(chars);
    }
 else {
      chars=new byte[length];
    }
  }
 else   if (chars.length < length) {
    chars=new byte[length];
  }
  return chars;
}
