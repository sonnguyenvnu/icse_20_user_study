@Override public void flush(){
  super.flush();
  cues=null;
  lastCues=null;
  currentWindow=0;
  currentCueBuilder=cueBuilders[currentWindow];
  resetCueBuilders();
  currentDtvCcPacket=null;
}
