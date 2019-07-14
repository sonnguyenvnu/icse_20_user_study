public static void updateWatchConnectionState(){
  try {
    Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability("remote_notifications",CapabilityClient.FILTER_REACHABLE).addOnCompleteListener(new OnCompleteListener<CapabilityInfo>(){
      @Override public void onComplete(      @NonNull Task<CapabilityInfo> task){
        watchConnected=false;
        try {
          CapabilityInfo capabilityInfo=task.getResult();
          if (capabilityInfo == null)           return;
          for (          Node node : capabilityInfo.getNodes()) {
            if (node.isNearby())             watchConnected=true;
          }
        }
 catch (        Exception ignore) {
        }
      }
    }
);
  }
 catch (  Throwable ignore) {
  }
}
