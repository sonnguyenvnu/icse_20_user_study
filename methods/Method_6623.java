public static String formatDateForBan(long date){
  try {
    date*=1000;
    Calendar rightNow=Calendar.getInstance();
    int year=rightNow.get(Calendar.YEAR);
    rightNow.setTimeInMillis(date);
    int dateYear=rightNow.get(Calendar.YEAR);
    if (year == dateYear) {
      return getInstance().formatterBannedUntilThisYear.format(new Date(date));
    }
 else {
      return getInstance().formatterBannedUntil.format(new Date(date));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR";
}
