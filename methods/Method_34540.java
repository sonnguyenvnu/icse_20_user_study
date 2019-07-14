public Observable<HystrixRequestEvents> getStream(){
  return HystrixRequestEventsStream.getInstance().observe();
}
