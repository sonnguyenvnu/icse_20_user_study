/** 
 * Returns a RxJava Observable for Geofence transitions
 * @param geofencingControl instance with the needed configuration
 * @return Observable for Geofence transitions (enter, exit, dwell)
 */
public static Observable<TransitionGeofence> from(final SmartLocation.GeofencingControl geofencingControl){
  return Observable.create(new ObservableOnSubscribe<TransitionGeofence>(){
    @Override public void subscribe(    final ObservableEmitter<TransitionGeofence> emitter){
      geofencingControl.start(new OnGeofencingTransitionListener(){
        @Override public void onGeofenceTransition(        TransitionGeofence transitionGeofence){
          emitter.onNext(transitionGeofence);
        }
      }
);
    }
  }
).doOnDispose(new Action(){
    @Override public void run(){
      geofencingControl.stop();
    }
  }
);
}
