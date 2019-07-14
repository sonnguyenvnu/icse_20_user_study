private static void applyDayNightThemeMaybe(boolean night){
  if (night) {
    if (currentTheme != currentNightTheme) {
      lastThemeSwitchTime=SystemClock.elapsedRealtime();
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme,currentNightTheme,true);
    }
  }
 else {
    if (currentTheme != currentDayTheme) {
      lastThemeSwitchTime=SystemClock.elapsedRealtime();
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme,currentDayTheme,true);
    }
  }
}
