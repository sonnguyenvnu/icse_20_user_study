public static void saveAutoNightThemeConfig(){
  SharedPreferences.Editor editor=MessagesController.getGlobalMainSettings().edit();
  editor.putInt("selectedAutoNightType",selectedAutoNightType);
  editor.putBoolean("autoNightScheduleByLocation",autoNightScheduleByLocation);
  editor.putFloat("autoNightBrighnessThreshold",autoNightBrighnessThreshold);
  editor.putInt("autoNightDayStartTime",autoNightDayStartTime);
  editor.putInt("autoNightDayEndTime",autoNightDayEndTime);
  editor.putInt("autoNightSunriseTime",autoNightSunriseTime);
  editor.putString("autoNightCityName",autoNightCityName);
  editor.putInt("autoNightSunsetTime",autoNightSunsetTime);
  editor.putLong("autoNightLocationLatitude3",Double.doubleToRawLongBits(autoNightLocationLatitude));
  editor.putLong("autoNightLocationLongitude3",Double.doubleToRawLongBits(autoNightLocationLongitude));
  editor.putInt("autoNightLastSunCheckDay",autoNightLastSunCheckDay);
  if (currentNightTheme != null) {
    editor.putString("nighttheme",currentNightTheme.name);
  }
 else {
    editor.remove("nighttheme");
  }
  editor.commit();
}
