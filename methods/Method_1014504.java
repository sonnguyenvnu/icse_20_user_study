@Override public void update(){
  age+=1;
  if (age > LIFETIME) {
    setDead(true);
  }
}
