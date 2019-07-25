public void init(byte[] data,int type){
  try {
    player=Manager.createPlayer(new ByteArrayInputStream(data),"audio/midi");
    postEvent(SOUND_UNINITIALIZED);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
