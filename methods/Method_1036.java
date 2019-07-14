private boolean isNetscape(){
  if (block.length < NETSCAPE.length) {
    return false;
  }
  for (int i=0, N=NETSCAPE.length; i < N; i++) {
    if (NETSCAPE[i] != (char)block[i]) {
      return false;
    }
  }
  return true;
}
