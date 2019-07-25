@Override public boolean open(){
  final Activity foregroundActivity=foregroundManager.getForegroundActivity();
  if (foregroundActivity != null) {
    return open(foregroundActivity);
  }
  return false;
}
