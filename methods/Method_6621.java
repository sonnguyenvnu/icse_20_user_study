public void recreateFormatters(){
  Locale locale=currentLocale;
  if (locale == null) {
    locale=Locale.getDefault();
  }
  String lang=locale.getLanguage();
  if (lang == null) {
    lang="en";
  }
  lang=lang.toLowerCase();
  isRTL=lang.length() == 2 && (lang.equals("ar") || lang.equals("fa") || lang.equals("he") || lang.equals("iw")) || lang.startsWith("ar_") || lang.startsWith("fa_") || lang.startsWith("he_") || lang.startsWith("iw_") || currentLocaleInfo != null && currentLocaleInfo.isRtl;
  nameDisplayOrder=lang.equals("ko") ? 2 : 1;
  formatterDayMonth=createFormatter(locale,getStringInternal("formatterMonth",R.string.formatterMonth),"dd MMM");
  formatterYear=createFormatter(locale,getStringInternal("formatterYear",R.string.formatterYear),"dd.MM.yy");
  formatterYearMax=createFormatter(locale,getStringInternal("formatterYearMax",R.string.formatterYearMax),"dd.MM.yyyy");
  chatDate=createFormatter(locale,getStringInternal("chatDate",R.string.chatDate),"d MMMM");
  chatFullDate=createFormatter(locale,getStringInternal("chatFullDate",R.string.chatFullDate),"d MMMM yyyy");
  formatterWeek=createFormatter(locale,getStringInternal("formatterWeek",R.string.formatterWeek),"EEE");
  formatterScheduleDay=createFormatter(locale,getStringInternal("formatDateScheduleDay",R.string.formatDateScheduleDay),"EEE MMM d");
  formatterDay=createFormatter(lang.toLowerCase().equals("ar") || lang.toLowerCase().equals("ko") ? locale : Locale.US,is24HourFormat ? getStringInternal("formatterDay24H",R.string.formatterDay24H) : getStringInternal("formatterDay12H",R.string.formatterDay12H),is24HourFormat ? "HH:mm" : "h:mm a");
  formatterStats=createFormatter(locale,is24HourFormat ? getStringInternal("formatterStats24H",R.string.formatterStats24H) : getStringInternal("formatterStats12H",R.string.formatterStats12H),is24HourFormat ? "MMM dd yyyy, HH:mm" : "MMM dd yyyy, h:mm a");
  formatterBannedUntil=createFormatter(locale,is24HourFormat ? getStringInternal("formatterBannedUntil24H",R.string.formatterBannedUntil24H) : getStringInternal("formatterBannedUntil12H",R.string.formatterBannedUntil12H),is24HourFormat ? "MMM dd yyyy, HH:mm" : "MMM dd yyyy, h:mm a");
  formatterBannedUntilThisYear=createFormatter(locale,is24HourFormat ? getStringInternal("formatterBannedUntilThisYear24H",R.string.formatterBannedUntilThisYear24H) : getStringInternal("formatterBannedUntilThisYear12H",R.string.formatterBannedUntilThisYear12H),is24HourFormat ? "MMM dd, HH:mm" : "MMM dd, h:mm a");
}
