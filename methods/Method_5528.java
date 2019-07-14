private void finalizeCurrentPacket(){
  if (currentDtvCcPacket == null) {
    return;
  }
  processCurrentPacket();
  currentDtvCcPacket=null;
}
