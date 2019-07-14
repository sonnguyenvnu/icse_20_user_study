@Override public void onChildCreated(Sprite... sprites){
  for (int i=0; i < sprites.length; i++) {
    sprites[i].setAnimationDelay(200 * (i + 1));
  }
}
