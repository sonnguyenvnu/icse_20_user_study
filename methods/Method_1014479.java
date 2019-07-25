@Override public void damage(int amount){
  int hullDamage=damageShield(amount);
  hp-=hullDamage;
  if (hp <= 0) {
    setDead(true);
  }
}
