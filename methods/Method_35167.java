private void destroyRouters(){
  if (!destroyed) {
    destroyed=true;
    if (activity != null) {
      for (      Router router : getRouters()) {
        router.onActivityDestroyed(activity);
      }
    }
  }
}
