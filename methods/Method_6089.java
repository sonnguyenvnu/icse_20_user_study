@TargetApi(17) private DefaultDisplayListener maybeBuildDefaultDisplayListenerV17(Context context){
  DisplayManager manager=(DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
  return manager == null ? null : new DefaultDisplayListener(manager);
}
