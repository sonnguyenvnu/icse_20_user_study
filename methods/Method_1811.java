private static String valueOrEmpty(int n){
  if (n == TO_END_OF_CONTENT) {
    return "";
  }
  return Integer.toString(n);
}
