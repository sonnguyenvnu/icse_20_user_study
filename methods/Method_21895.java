private List<ResolveInfo> getAllSampleActivities(){
  Intent filter=new Intent();
  filter.setAction(Intent.ACTION_RUN);
  filter.addCategory(CATEGORY_SAMPLE);
  return getPackageManager().queryIntentActivities(filter,0);
}
