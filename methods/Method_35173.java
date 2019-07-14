@Override public void onActivityCreated(Activity activity,Bundle savedInstanceState){
  if (this.activity == null && findInActivity(activity) == LifecycleHandler.this) {
    this.activity=activity;
    for (    ActivityHostedRouter router : new ArrayList<>(routerMap.values())) {
      router.onContextAvailable();
    }
  }
}
