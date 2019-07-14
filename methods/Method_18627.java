static boolean areTransitionsEnabled(Context context){
  if (!ComponentsConfiguration.ARE_TRANSITIONS_SUPPORTED) {
    return false;
  }
  if (!ComponentsConfiguration.isEndToEndTestRun) {
    return true;
  }
  if (!ComponentsConfiguration.CAN_CHECK_GLOBAL_ANIMATOR_SETTINGS) {
    return false;
  }
  float animatorDurationScale=Settings.Global.getFloat(context.getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE,1f);
  return ComponentsConfiguration.forceEnableTransitionsForInstrumentationTests || animatorDurationScale != 0f;
}
