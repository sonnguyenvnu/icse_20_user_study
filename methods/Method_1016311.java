@Override public boolean release(int resources){
  if (acquiredResources - resources >= 0) {
    acquiredResources-=resources;
    return true;
  }
  return false;
}
