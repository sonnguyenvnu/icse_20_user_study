private void onAccess(long key){
  sample++;
  if (Math.floorMod(hasher.hashLong(key).asInt(),R) < 1) {
    for (    WindowTinyLfuPolicy policy : minis) {
      policy.record(key);
    }
  }
}
