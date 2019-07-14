public boolean canUpgrate(){
  return (peerCapabilities & VoIPController.PEER_CAP_GROUP_CALLS) == VoIPController.PEER_CAP_GROUP_CALLS;
}
