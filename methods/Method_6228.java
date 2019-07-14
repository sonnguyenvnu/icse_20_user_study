@Override protected void onHandleIntent(Intent intent){
  Intent intent2=new Intent(this,LaunchActivity.class);
  intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  startActivity(intent2);
}
