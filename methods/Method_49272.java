@Override public boolean hasDefaultConfiguration(){
  return !isPartitioned() && !isStatic();
}
