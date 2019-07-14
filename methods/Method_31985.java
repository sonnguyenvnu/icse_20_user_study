private static String[] realignDaysOfWeek(String[] daysOfWeek){
  String[] a=new String[8];
  for (int i=1; i < 8; i++) {
    a[i]=daysOfWeek[(i < 7) ? i + 1 : 1];
  }
  return a;
}
