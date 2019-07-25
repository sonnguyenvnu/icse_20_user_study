@Override public void heal(int amount){
  hp=Math.min(getMaxHp(),hp + amount);
}
