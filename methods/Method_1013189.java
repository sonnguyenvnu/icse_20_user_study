public String Unchanged(){
  String s="";
  for (int i=0; i < count.length; i++)   if (count[i] == 0)   s=s + ((s.length() == 0) ? "" : ", ") + (String)vars.elementAt(i);
  return s;
}
