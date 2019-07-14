public void onDestroy(){
  if (isAutoDetectIdle) {
    ((Application)this.mContext.getApplicationContext()).unregisterActivityLifecycleCallbacks(mLifecycleCallbacks);
  }
}
