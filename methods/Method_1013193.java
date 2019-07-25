public static void Copy(Vector in,Vector out){
  out.removeAllElements();
  int i=0;
  while (i < in.size()) {
    out.addElement(in.elementAt(i));
    i=i + 1;
  }
  ;
  return;
}
