static String count(ArrayList mylist){
  int a=0, b=0, c=0;
  for (int i=0; i < mylist.size(); i++) {
    Object element=mylist.get(i);
    if (element instanceof Student)     a++;
    if (element instanceof Rockstar)     b++;
    if (element instanceof Hacker)     c++;
  }
  String ret=Integer.toString(a) + " " + Integer.toString(b) + " " + Integer.toString(c);
  return ret;
}
