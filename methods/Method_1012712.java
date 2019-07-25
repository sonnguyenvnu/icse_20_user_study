String match(String str){
  if (!isEmpty()) {
    if (modified) {
      compile();
    }
    if (combo.reset(str).find()) {
      for (int i=0; i < combo.groupCount(); i++) {
        if (combo.group(i + 1) != null) {
          return groupmap.get(i);
        }
      }
    }
  }
  return null;
}
