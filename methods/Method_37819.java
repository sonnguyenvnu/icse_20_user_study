public static boolean equalsToLowercase(final CharSequence charSequence,final CharSequence name){
  int len=charSequence.length();
  if (len != name.length()) {
    return false;
  }
  if (compare(charSequence,name,len))   return false;
  return true;
}
