@GuardedBy("lock") protected int find(PingData data){
  if (data == null)   return -1;
  for (int i=0; i < index; i++) {
    if (data.equals(ping_rsps[i]))     return i;
  }
  return -1;
}
