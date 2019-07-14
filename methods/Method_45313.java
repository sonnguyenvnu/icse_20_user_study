private static int getGlobIndex(final Path path){
  int nameCount=path.getNameCount();
  for (int i=0; i < nameCount; i++) {
    String current=path.getName(i).toString();
    int length=current.length();
    for (int j=0; j < length; j++) {
      if (isGlobMeta(current.charAt(j))) {
        return i;
      }
    }
  }
  return -1;
}
