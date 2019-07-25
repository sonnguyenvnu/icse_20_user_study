/** 
 * Returns a RxJava Observable for Location changes
 * @param locationControl instance with the needed configuration
 * @return Observable for Location changes
 */
public static Observable<Location> from(final SmartLocation.LocationControl locationControl){
  return Observable.create(new ObservableOnSubscribe<Location>(){
    @Override public void subscribe(    final ObservableEmitter<Location> emitter) throws Exception {
      locationControl.start(new OnLocationUpdatedListener(){
        @Override public void onLocationUpdated(        Location location){
          emitter.onNext(location);
        }
      }
);
    }
  }
).doOnDispose(new Action(){
    @Override public void run() throws Exception {
      locationControl.stop();
    }
  }
);
}
