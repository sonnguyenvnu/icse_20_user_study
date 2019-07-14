public static long getSecondsBetweenDates(Date d1,Date d2){
  return Math.abs((d1.getTime() - d2.getTime()) / 1000);
}
