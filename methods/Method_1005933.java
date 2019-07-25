@Override public boolean close(){
  final Activity foregroundActivity=foregroundManager.getForegroundActivity();
  if (foregroundActivity != null) {
    return close(foregroundActivity);
  }
  return false;
}
