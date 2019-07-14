@NonNull public static LifecycleHandler install(@NonNull Activity activity){
  LifecycleHandler lifecycleHandler=findInActivity(activity);
  if (lifecycleHandler == null) {
    lifecycleHandler=new LifecycleHandler();
    activity.getFragmentManager().beginTransaction().add(lifecycleHandler,FRAGMENT_TAG).commit();
  }
  lifecycleHandler.registerActivityListener(activity);
  return lifecycleHandler;
}
