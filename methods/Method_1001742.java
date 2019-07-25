public synchronized static void clear(){
  if (last != null) {
    for (int i=0; i < last.length(); i++) {
      System.err.print('\b');
    }
    for (int i=0; i < last.length(); i++) {
      System.err.print(' ');
    }
    for (int i=0; i < last.length(); i++) {
      System.err.print('\b');
    }
  }
  last=null;
}
