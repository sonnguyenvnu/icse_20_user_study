static public boolean containsTweakComment(String[] codeTabs){
  for (  String tab : codeTabs) {
    if (hasTweakComment(tab)) {
      return true;
    }
  }
  return false;
}
