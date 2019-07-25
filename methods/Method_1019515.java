@Override public boolean next(){
  while (++index < arrLongs) {
    hash=cache[index];
    if ((hash != 0) && (hash < thetaLong)) {
      return true;
    }
  }
  return false;
}
