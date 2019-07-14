public static boolean isDeviceAnimationEnabled(@NonNull Context context){
  float duration=Settings.Global.getFloat(context.getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE,1);
  float transition=Settings.Global.getFloat(context.getContentResolver(),Settings.Global.TRANSITION_ANIMATION_SCALE,1);
  return (duration != 0 && transition != 0);
}
