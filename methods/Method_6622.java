public static String formatSectionDate(long date){
  try {
    date*=1000;
    Calendar rightNow=Calendar.getInstance();
    int year=rightNow.get(Calendar.YEAR);
    rightNow.setTimeInMillis(date);
    int dateYear=rightNow.get(Calendar.YEAR);
    int month=rightNow.get(Calendar.MONTH);
    final String[] months=new String[]{LocaleController.getString("January",R.string.January),LocaleController.getString("February",R.string.February),LocaleController.getString("March",R.string.March),LocaleController.getString("April",R.string.April),LocaleController.getString("May",R.string.May),LocaleController.getString("June",R.string.June),LocaleController.getString("July",R.string.July),LocaleController.getString("August",R.string.August),LocaleController.getString("September",R.string.September),LocaleController.getString("October",R.string.October),LocaleController.getString("November",R.string.November),LocaleController.getString("December",R.string.December)};
    if (year == dateYear) {
      return months[month];
    }
 else {
      return months[month] + " " + dateYear;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR";
}
