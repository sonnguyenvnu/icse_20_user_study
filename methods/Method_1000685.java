public LinkedCharArray push(String s){
  char[] cs=s.toCharArray();
  for (  char c : cs)   push(c);
  return this;
}
