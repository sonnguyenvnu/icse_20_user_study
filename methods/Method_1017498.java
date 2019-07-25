@Nullable @Override public String title(Resources resources){
  if (taskId() != null) {
    return resources.getString(R.string.edit_task);
  }
 else {
    return resources.getString(R.string.add_task);
  }
}
