private String newTriggerId(){
  long r=random.nextLong();
  if (r < 0) {
    r=-r;
  }
  return "MT_" + Long.toString(r,30 + (int)(System.currentTimeMillis() % 7));
}
