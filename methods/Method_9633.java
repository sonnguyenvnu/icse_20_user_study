private String getLocationSunString(){
  int currentHour=Theme.autoNightSunriseTime / 60;
  int currentMinute=(Theme.autoNightSunriseTime - currentHour * 60);
  String sunriseTimeStr=String.format("%02d:%02d",currentHour,currentMinute);
  currentHour=Theme.autoNightSunsetTime / 60;
  currentMinute=(Theme.autoNightSunsetTime - currentHour * 60);
  String sunsetTimeStr=String.format("%02d:%02d",currentHour,currentMinute);
  return LocaleController.formatString("AutoNightUpdateLocationInfo",R.string.AutoNightUpdateLocationInfo,sunsetTimeStr,sunriseTimeStr);
}
