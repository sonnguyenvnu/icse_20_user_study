public void registerForActivityResult(@NonNull String instanceId,int requestCode){
  activityRequestMap.put(requestCode,instanceId);
}
