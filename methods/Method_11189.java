@Override public Sprite[] onCreateChild(){
  WaveItem[] waveItems=new WaveItem[5];
  for (int i=0; i < waveItems.length; i++) {
    waveItems[i]=new WaveItem();
    waveItems[i].setAnimationDelay(-1200 + i * 100);
  }
  return waveItems;
}
