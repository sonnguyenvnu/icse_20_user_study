public static Drawable getCurrentHolidayDrawable(){
  if ((System.currentTimeMillis() - lastHolidayCheckTime) >= 60 * 1000) {
    lastHolidayCheckTime=System.currentTimeMillis();
    Calendar calendar=Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int monthOfYear=calendar.get(Calendar.MONTH);
    int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
    int minutes=calendar.get(Calendar.MINUTE);
    int hour=calendar.get(Calendar.HOUR_OF_DAY);
    if (monthOfYear == 0 && dayOfMonth == 1 && minutes <= 10 && hour == 0) {
      canStartHolidayAnimation=true;
    }
 else {
      canStartHolidayAnimation=false;
    }
    if (dialogs_holidayDrawable == null) {
      if (monthOfYear == 11 && dayOfMonth >= (BuildVars.DEBUG_PRIVATE_VERSION ? 29 : 31) && dayOfMonth <= 31 || monthOfYear == 0 && dayOfMonth == 1) {
        dialogs_holidayDrawable=ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.newyear);
        dialogs_holidayDrawableOffsetX=-AndroidUtilities.dp(3);
        dialogs_holidayDrawableOffsetY=-AndroidUtilities.dp(1);
      }
    }
  }
  return dialogs_holidayDrawable;
}
