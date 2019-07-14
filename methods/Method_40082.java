public boolean isImportStar(){
  return names.size() == 1 && "*".equals(names.get(0).name.get(0).id);
}
