public void monitor(){
  if (isFirstLaunch(context)) {
    setInstallDate(context);
  }
  PreferenceHelper.setLaunchTimes(context,getLaunchTimes(context) + 1);
}
