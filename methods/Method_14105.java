protected String asciify(String s){
  char[] c=s.toCharArray();
  StringBuffer b=new StringBuffer();
  for (  char element : c) {
    b.append(translate(element));
  }
  return b.toString();
}
