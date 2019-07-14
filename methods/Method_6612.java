public static String formatCallDuration(int duration){
  if (duration > 3600) {
    String result=LocaleController.formatPluralString("Hours",duration / 3600);
    int minutes=duration % 3600 / 60;
    if (minutes > 0) {
      result+=", " + LocaleController.formatPluralString("Minutes",minutes);
    }
    return result;
  }
 else   if (duration > 60) {
    return LocaleController.formatPluralString("Minutes",duration / 60);
  }
 else {
    return LocaleController.formatPluralString("Seconds",duration);
  }
}
