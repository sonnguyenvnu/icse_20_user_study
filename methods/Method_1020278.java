public void init(int freq,long duration){
  try {
    player=Manager.createPlayer(null,null);
    postEvent(SOUND_UNINITIALIZED);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
