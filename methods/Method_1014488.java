@Override public void heal(int amount){
  hp+=amount;
  if (hp > maxHp) {
    hp=maxHp;
  }
}
