String merge(String full){
  while (true) {
    int len=full.length();
    full=full.replaceFirst("[^/]+/\\.\\./","");
    if (full.length() == len) {
      return full;
    }
  }
}
