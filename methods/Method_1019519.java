@Override public boolean next(){
  while (++index < arrLongs) {
    hash=mem.getLong(offsetBytes + (index << 3));
    if ((hash != 0) && (hash < thetaLong)) {
      return true;
    }
  }
  return false;
}
