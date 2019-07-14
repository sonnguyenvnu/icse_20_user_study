public static boolean equals(final CharSequence charSequence1,final CharSequence charSequence2){
  int len=charSequence1.length();
  if (len != charSequence2.length()) {
    return false;
  }
  for (int i=0; i < len; i++) {
    if (charSequence1.charAt(i) != charSequence2.charAt(i)) {
      return false;
    }
  }
  return true;
}
