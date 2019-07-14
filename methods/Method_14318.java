static public long allocateID(){
  return Math.round(Math.random() * 1000000) + System.currentTimeMillis();
}
