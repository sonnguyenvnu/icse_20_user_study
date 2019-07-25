@Override public void hide(){
  if (delay >= 0 && obscuresVirtualScreen) {
synchronized (waiter) {
      waiter.notifyAll();
    }
  }
}
