private static char[] allocateChars(int length){
  char[] chars=charsLocal.get();
  if (chars == null) {
    if (length <= 1024 * 64) {
      chars=new char[1024 * 64];
      charsLocal.set(chars);
    }
 else {
      chars=new char[length];
    }
  }
 else   if (chars.length < length) {
    chars=new char[length];
  }
  return chars;
}
