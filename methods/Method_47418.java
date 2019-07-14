private static int indexOfDifferenceStrings(CharSequence cs1,CharSequence cs2){
  if (cs1 == cs2)   return INDEX_NOT_FOUND;
  if (cs1 == null || cs2 == null)   return 0;
  int i;
  for (i=0; i < cs1.length() && i < cs2.length(); ++i) {
    if (cs1.charAt(i) != cs2.charAt(i))     break;
  }
  if (i < cs2.length() || i < cs1.length())   return i;
  return INDEX_NOT_FOUND;
}
