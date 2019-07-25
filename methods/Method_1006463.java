@Deprecated @Override public void remove(int rangeStart,int rangeEnd){
  resetCache();
  super.remove(rangeStart,rangeEnd);
}
