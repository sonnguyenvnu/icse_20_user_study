private static String[] realignMonths(String[] months){
  String[] a=new String[13];
  for (int i=1; i < 13; i++) {
    a[i]=months[i - 1];
  }
  return a;
}
