@Override public void increment(long e){
  if (tcs.countItem(e) < maxcount) {
    tcs.addItem(e);
  }
}
