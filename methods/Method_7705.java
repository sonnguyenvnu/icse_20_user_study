public static void checkAutoNightThemeConditions(boolean force){
  if (previousTheme != null) {
    return;
  }
  if (force) {
    if (switchNightRunnableScheduled) {
      switchNightRunnableScheduled=false;
      AndroidUtilities.cancelRunOnUIThread(switchNightBrightnessRunnable);
    }
    if (switchDayRunnableScheduled) {
      switchDayRunnableScheduled=false;
      AndroidUtilities.cancelRunOnUIThread(switchDayBrightnessRunnable);
    }
  }
  if (selectedAutoNightType != AUTO_NIGHT_TYPE_AUTOMATIC) {
    if (switchNightRunnableScheduled) {
      switchNightRunnableScheduled=false;
      AndroidUtilities.cancelRunOnUIThread(switchNightBrightnessRunnable);
    }
    if (switchDayRunnableScheduled) {
      switchDayRunnableScheduled=false;
      AndroidUtilities.cancelRunOnUIThread(switchDayBrightnessRunnable);
    }
    if (lightSensorRegistered) {
      lastBrightnessValue=1.0f;
      sensorManager.unregisterListener(ambientSensorListener,lightSensor);
      lightSensorRegistered=false;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("light sensor unregistered");
      }
    }
  }
  int switchToTheme=0;
  if (selectedAutoNightType == AUTO_NIGHT_TYPE_SCHEDULED) {
    Calendar calendar=Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int time=calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    int timeStart;
    int timeEnd;
    if (autoNightScheduleByLocation) {
      int day=calendar.get(Calendar.DAY_OF_MONTH);
      if (autoNightLastSunCheckDay != day && autoNightLocationLatitude != 10000 && autoNightLocationLongitude != 10000) {
        int[] t=SunDate.calculateSunriseSunset(Theme.autoNightLocationLatitude,Theme.autoNightLocationLongitude);
        autoNightSunriseTime=t[0];
        autoNightSunsetTime=t[1];
        autoNightLastSunCheckDay=day;
        saveAutoNightThemeConfig();
      }
      timeStart=autoNightSunsetTime;
      timeEnd=autoNightSunriseTime;
    }
 else {
      timeStart=autoNightDayStartTime;
      timeEnd=autoNightDayEndTime;
    }
    if (timeStart < timeEnd) {
      if (timeStart <= time && time <= timeEnd) {
        switchToTheme=2;
      }
 else {
        switchToTheme=1;
      }
    }
 else {
      if (timeStart <= time && time <= 24 * 60 || 0 <= time && time <= timeEnd) {
        switchToTheme=2;
      }
 else {
        switchToTheme=1;
      }
    }
  }
 else   if (selectedAutoNightType == AUTO_NIGHT_TYPE_AUTOMATIC) {
    if (lightSensor == null) {
      sensorManager=(SensorManager)ApplicationLoader.applicationContext.getSystemService(Context.SENSOR_SERVICE);
      lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    if (!lightSensorRegistered && lightSensor != null) {
      sensorManager.registerListener(ambientSensorListener,lightSensor,500000);
      lightSensorRegistered=true;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("light sensor registered");
      }
    }
    if (lastBrightnessValue <= autoNightBrighnessThreshold) {
      if (!switchNightRunnableScheduled) {
        switchToTheme=2;
      }
    }
 else {
      if (!switchDayRunnableScheduled) {
        switchToTheme=1;
      }
    }
  }
 else   if (selectedAutoNightType == AUTO_NIGHT_TYPE_NONE) {
    switchToTheme=1;
  }
  if (switchToTheme != 0) {
    applyDayNightThemeMaybe(switchToTheme == 2);
  }
  if (force) {
    lastThemeSwitchTime=0;
  }
}
