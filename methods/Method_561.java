public final boolean charArrayCompare(char[] chars){
  for (int i=0; i < chars.length; ++i) {
    if (charAt(bp + i) != chars[i]) {
      return false;
    }
  }
  return true;
}
