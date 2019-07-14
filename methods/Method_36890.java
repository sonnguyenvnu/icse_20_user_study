@Override public void register(int interval,OnTickListener onTickListener,boolean intermediate){
  mListeners.put(onTickListener,new IntervalTickListener(interval,onTickListener,intermediate));
  start(false);
}
