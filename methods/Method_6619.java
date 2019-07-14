public static String formatDateOnline(long date){
  try {
    date*=1000;
    Calendar rightNow=Calendar.getInstance();
    int day=rightNow.get(Calendar.DAY_OF_YEAR);
    int year=rightNow.get(Calendar.YEAR);
    rightNow.setTimeInMillis(date);
    int dateDay=rightNow.get(Calendar.DAY_OF_YEAR);
    int dateYear=rightNow.get(Calendar.YEAR);
    if (dateDay == day && year == dateYear) {
      return LocaleController.formatString("LastSeenFormatted",R.string.LastSeenFormatted,LocaleController.formatString("TodayAtFormatted",R.string.TodayAtFormatted,getInstance().formatterDay.format(new Date(date))));
    }
 else     if (dateDay + 1 == day && year == dateYear) {
      return LocaleController.formatString("LastSeenFormatted",R.string.LastSeenFormatted,LocaleController.formatString("YesterdayAtFormatted",R.string.YesterdayAtFormatted,getInstance().formatterDay.format(new Date(date))));
    }
 else     if (Math.abs(System.currentTimeMillis() - date) < 31536000000L) {
      String format=LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,getInstance().formatterDayMonth.format(new Date(date)),getInstance().formatterDay.format(new Date(date)));
      return LocaleController.formatString("LastSeenDateFormatted",R.string.LastSeenDateFormatted,format);
    }
 else {
      String format=LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,getInstance().formatterYear.format(new Date(date)),getInstance().formatterDay.format(new Date(date)));
      return LocaleController.formatString("LastSeenDateFormatted",R.string.LastSeenDateFormatted,format);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR";
}
