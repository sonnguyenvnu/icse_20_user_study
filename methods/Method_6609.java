public static String formatTTLString(int ttl){
  if (ttl < 60) {
    return LocaleController.formatPluralString("Seconds",ttl);
  }
 else   if (ttl < 60 * 60) {
    return LocaleController.formatPluralString("Minutes",ttl / 60);
  }
 else   if (ttl < 60 * 60 * 24) {
    return LocaleController.formatPluralString("Hours",ttl / 60 / 60);
  }
 else   if (ttl < 60 * 60 * 24 * 7) {
    return LocaleController.formatPluralString("Days",ttl / 60 / 60 / 24);
  }
 else {
    int days=ttl / 60 / 60 / 24;
    if (ttl % 7 == 0) {
      return LocaleController.formatPluralString("Weeks",days / 7);
    }
 else {
      return String.format("%s %s",LocaleController.formatPluralString("Weeks",days / 7),LocaleController.formatPluralString("Days",days % 7));
    }
  }
}
