private boolean isNotValidNameString(String src){
  if (TextUtils.isEmpty(src)) {
    return true;
  }
  int count=0;
  for (int a=0, len=src.length(); a < len; a++) {
    char c=src.charAt(a);
    if (c >= '0' && c <= '9') {
      count++;
    }
  }
  return count > 3;
}
