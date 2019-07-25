@Override public void bind(boolean wholeCore){
  super.bind(wholeCore);
  Thread thread=Thread.currentThread();
  changeGroupOfThread(thread,new ThreadTrackingGroup(thread.getThreadGroup(),this));
}
