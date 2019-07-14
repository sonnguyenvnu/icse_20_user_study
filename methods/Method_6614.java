public static String formatDateChat(long date){
  try {
    Calendar rightNow=Calendar.getInstance();
    date*=1000;
    rightNow.setTimeInMillis(date);
    if (Math.abs(System.currentTimeMillis() - date) < 31536000000L) {
      return getInstance().chatDate.format(date);
    }
    return getInstance().chatFullDate.format(date);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR: formatDateChat";
}
