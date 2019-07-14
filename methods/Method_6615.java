public static String formatDate(long date){
  try {
    date*=1000;
    Calendar rightNow=Calendar.getInstance();
    int day=rightNow.get(Calendar.DAY_OF_YEAR);
    int year=rightNow.get(Calendar.YEAR);
    rightNow.setTimeInMillis(date);
    int dateDay=rightNow.get(Calendar.DAY_OF_YEAR);
    int dateYear=rightNow.get(Calendar.YEAR);
    if (dateDay == day && year == dateYear) {
      return getInstance().formatterDay.format(new Date(date));
    }
 else     if (dateDay + 1 == day && year == dateYear) {
      return getString("Yesterday",R.string.Yesterday);
    }
 else     if (Math.abs(System.currentTimeMillis() - date) < 31536000000L) {
      return getInstance().formatterDayMonth.format(new Date(date));
    }
 else {
      return getInstance().formatterYear.format(new Date(date));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR: formatDate";
}
