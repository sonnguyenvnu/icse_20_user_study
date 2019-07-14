static int encodeUTF16(CharSequence text,boolean nullTerminated,long target){
  int len=text.length();
  for (int i=0; i < len; i++) {
    UNSAFE.putShort(target + Integer.toUnsignedLong(i) * 2,(short)text.charAt(i));
  }
  if (nullTerminated) {
    UNSAFE.putShort(target + Integer.toUnsignedLong(len++) * 2,(short)0);
  }
  return 2 * len;
}
