@Override public void damage(int amount){
  hp-=amount;
  if (hp < 0) {
    setDead(true);
  }
}
