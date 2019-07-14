public void freezeConfiguration(){
  config.set(FROZEN_KEY,Boolean.TRUE);
  if (!isFrozen())   setFrozen();
}
