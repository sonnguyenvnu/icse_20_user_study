public boolean isProjectActivity(){
  if (activity() != null) {
    return PROJECT_NOTIFICATION_CATEGORIES.contains(activity().category());
  }
  return false;
}
