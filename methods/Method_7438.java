public static void sendMessageToWatch(final String path,final byte[] data,String capability){
  Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability(capability,CapabilityClient.FILTER_REACHABLE).addOnCompleteListener(new OnCompleteListener<CapabilityInfo>(){
    @Override public void onComplete(    @NonNull Task<CapabilityInfo> task){
      CapabilityInfo info=task.getResult();
      if (info != null) {
        MessageClient mc=Wearable.getMessageClient(ApplicationLoader.applicationContext);
        Set<Node> nodes=info.getNodes();
        for (        Node node : nodes) {
          mc.sendMessage(node.getId(),path,data);
        }
      }
    }
  }
);
}
