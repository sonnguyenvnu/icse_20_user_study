@Override public void update(){
  age++;
  if (settlement == null) {
    setDead(true);
  }
  heal(hpRegenerationRate);
}
