int contains(String a,ArrayList<String[]> b){
  int i=0;
  for (  String[] x : b) {
    if (x[1].equals(a))     return i;
    i++;
  }
  return -1;
}
