public boolean contains(String string){
  String[] lines=read().split("\n");
  for (  String line : lines) {
    if (string.equals(line.trim())) {
      return true;
    }
  }
  return false;
}
