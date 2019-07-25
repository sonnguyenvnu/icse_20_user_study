public void meta(MetaMessage message){
  if (message.getType() == 47) {
    beatEvent();
    sequencer.start();
    setBPM(getBPM());
  }
}
