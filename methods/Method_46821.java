public void invalidateRecentsColorAndIcon(){
  if (SDK_INT >= 21) {
    @ColorInt int primaryColor=ColorPreferenceHelper.getPrimary(getCurrentColorPreference(),MainActivity.currentTab);
    ActivityManager.TaskDescription taskDescription=new ActivityManager.TaskDescription("Amaze",((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap(),primaryColor);
    setTaskDescription(taskDescription);
  }
}
