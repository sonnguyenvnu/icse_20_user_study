public Vector Unchanged(int ch){
  Vector sv=new Vector();
  String s="";
  boolean haveOne=false;
  for (int i=0; i < count.length; i++)   if (count[i] == 0) {
    String one=(String)vars.elementAt(i);
    if (haveOne)     s=s + ", ";
 else     haveOne=true;
    if (s.length() + one.length() > ch) {
      if (s.length() > 0)       sv.addElement(s);
      s=one;
    }
 else     s=s + one;
  }
  if (s.length() > 0)   sv.addElement(s);
  return sv;
}
