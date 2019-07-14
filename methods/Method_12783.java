protected Activity newActivity(Activity activity){
synchronized (mActivities) {
    for (int i=mActivities.size() - 1; i >= 0; i--) {
      if (mActivities.get(i).get() == null) {
        mActivities.remove(i);
      }
    }
    mActivities.add(new WeakReference<>(activity));
  }
  return activity;
}
