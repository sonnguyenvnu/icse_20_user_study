static int encodeASCII(CharSequence text,boolean nullTerminated,long target){
  int len=text.length();
  for (int p=0; p < len; p++) {
    UNSAFE.putByte(target + p,(byte)text.charAt(p));
  }
  if (nullTerminated) {
    UNSAFE.putByte(target + len++,(byte)0);
  }
  return len;
}
