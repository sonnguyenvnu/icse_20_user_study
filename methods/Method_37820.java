public static boolean startsWithLowercase(final CharSequence charSequence,final CharSequence chars){
  int length=chars.length();
  if (charSequence.length() < length) {
    return false;
  }
  if (compare(charSequence,chars,length))   return false;
  return true;
}
