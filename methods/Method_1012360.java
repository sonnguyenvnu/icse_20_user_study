public static void start(Activity activity,LocationEvent locationEvent,int requestCode){
  Intent intent=new Intent(activity,NearbyListActivity.class);
  intent.putExtra(ConstantUtil.LOCATION,locationEvent);
  activity.startActivityForResult(intent,requestCode);
}
