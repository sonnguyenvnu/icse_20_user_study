public void bind(Broadcast broadcast){
  if (broadcast.isSimpleRebroadcast()) {
    boolean isSimpleRebroadcastByOneself=broadcast.isSimpleRebroadcastByOneself();
    boolean isUnrebroadcasting=isSimpleRebroadcastByOneself && DeleteBroadcastManager.getInstance().isWriting(broadcast.id);
    if (broadcast.parentBroadcast != null) {
      bind(broadcast.parentBroadcast,broadcast.rebroadcastedBroadcast,isSimpleRebroadcastByOneself,isUnrebroadcasting);
    }
 else {
      bind(broadcast.rebroadcastedBroadcast,broadcast.rebroadcastedBroadcast.rebroadcastedBroadcast,isSimpleRebroadcastByOneself,isUnrebroadcasting);
    }
  }
 else {
    bind(broadcast,broadcast.rebroadcastedBroadcast,false,false);
  }
}
