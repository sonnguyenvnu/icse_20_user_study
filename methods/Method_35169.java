public void unregisterForActivityResults(@NonNull String instanceId){
  for (int i=activityRequestMap.size() - 1; i >= 0; i--) {
    if (instanceId.equals(activityRequestMap.get(activityRequestMap.keyAt(i)))) {
      activityRequestMap.removeAt(i);
    }
  }
}
