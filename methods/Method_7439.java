@Override public void onCapabilityChanged(CapabilityInfo capabilityInfo){
  if ("remote_notifications".equals(capabilityInfo.getName())) {
    watchConnected=false;
    for (    Node node : capabilityInfo.getNodes()) {
      if (node.isNearby())       watchConnected=true;
    }
  }
}
