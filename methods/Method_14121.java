/** 
 * Tests whether the word is the root or a regular english inflection of it, e.g. "ache", "achy", "aches", "ached", "aching", "achingly" This is for cases where we want to match only the root and corresponding inflected forms, and not completely different words which may have the same substring in them.
 */
boolean RootOrInflections(String inWord,String root){
  int len=root.length();
  String test;
  test=root + "S";
  if ((inWord.equals(root)) || (inWord.equals(test))) {
    return true;
  }
  if (root.charAt(len - 1) != 'E') {
    test=root + "ES";
  }
  if (inWord.equals(test)) {
    return true;
  }
  if (root.charAt(len - 1) != 'E') {
    test=root + "ED";
  }
 else {
    test=root + "D";
  }
  if (inWord.equals(test)) {
    return true;
  }
  if (root.charAt(len - 1) == 'E') {
    root=root.substring(0,len - 1);
  }
  test=root + "ING";
  if (inWord.equals(test)) {
    return true;
  }
  test=root + "INGLY";
  if (inWord.equals(test)) {
    return true;
  }
  test=root + "Y";
  if (inWord.equals(test)) {
    return true;
  }
  return false;
}
