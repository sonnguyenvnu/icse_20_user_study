static public String[] getMonoFontFamilies(){
  StringList families=new StringList();
  for (  Font font : getMonoFontList()) {
    families.appendUnique(font.getFamily());
  }
  families.sort();
  return families.array();
}
