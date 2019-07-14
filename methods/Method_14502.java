static public long generateID(){
  return System.currentTimeMillis() + Math.round(Math.random() * 1000000000000L);
}
