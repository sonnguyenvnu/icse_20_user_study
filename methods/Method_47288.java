public int containsServer(String path){
synchronized (servers) {
    if (servers == null)     return -1;
    int i=0;
    for (    String[] x : servers) {
      if (x[1].equals(path))       return i;
      i++;
    }
  }
  return -1;
}
