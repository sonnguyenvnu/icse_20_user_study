private static String getFloodWaitString(String error){
  int time=Utilities.parseInt(error);
  String timeString;
  if (time < 60) {
    timeString=LocaleController.formatPluralString("Seconds",time);
  }
 else {
    timeString=LocaleController.formatPluralString("Minutes",time / 60);
  }
  return LocaleController.formatString("FloodWaitTime",R.string.FloodWaitTime,timeString);
}
