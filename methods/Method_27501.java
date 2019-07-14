protected void setTaskName(@Nullable String name){
  setTaskDescription(new ActivityManager.TaskDescription(name,null,ViewHelper.getPrimaryDarkColor(this)));
}
