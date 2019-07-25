private static Display clean(Display orig){
  if (orig.size() == 0 || orig.get(0) instanceof MessageNumber == false) {
    return orig;
  }
  Display result=Display.empty();
  for (int i=0; i < orig.size(); i++) {
    CharSequence element=orig.get(i);
    if (i == 1) {
      element=removeTag(orig.get(0).toString()) + " " + element;
    }
    if (i != 0) {
      result=result.add(element);
    }
  }
  return result;
}
