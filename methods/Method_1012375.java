public void start(Class aClass,boolean finish){
  if (mMap.values().size() > 0) {
    Activity activity=mMap.values().iterator().next();
    Intent intent=new Intent(activity,aClass);
    activity.startActivity(intent);
    if (finish) {
      activity.finish();
    }
  }
 else {
    Intent intent=new Intent();
    intent.setClass(BaseApplication.getInstance(),aClass);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    BaseApplication.getInstance().startActivity(intent);
  }
}
