public void pick(Activity activity,int requestCode){
  activity.startActivityForResult(buildPickIntent(activity),requestCode);
}
