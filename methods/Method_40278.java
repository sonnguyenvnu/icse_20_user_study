public boolean isImportStar(){
  return aliases.size() == 1 && "*".equals(aliases.get(0).name);
}
