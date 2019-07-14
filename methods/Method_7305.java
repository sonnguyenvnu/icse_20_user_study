public void savePassword(byte[] hash,byte[] salted){
  savedPasswordTime=SystemClock.elapsedRealtime();
  savedPasswordHash=hash;
  savedSaltedPassword=salted;
}
