public String Unchanged(Changed c){
  String s="";
  for (int i=0; i < count.length; i++)   if ((count[i] == 0) && c.count[i] > 0)   s=s + ((s.length() == 0) ? "" : ", ") + (String)vars.elementAt(i);
  return s;
}
