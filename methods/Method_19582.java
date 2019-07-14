public static String getTimeString(long time,boolean omitSuffix){
  final boolean isAM=time < TWELVE_HOURS;
  int hours=getHours(time);
  if (hours == 0 && !isAM) {
    hours=12;
  }
  final String s=String.format("%02d:%02d",hours,getMinutes(time));
  if (omitSuffix) {
    return s;
  }
  return s + (isAM ? " AM" : " PM");
}
