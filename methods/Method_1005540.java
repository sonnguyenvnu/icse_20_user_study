/** 
 * Returns a RxJava Observable for Activity Recognition changes
 * @param activityControl instance with the needed configuration
 * @return Observable for Activity changes
 */
public static Observable<DetectedActivity> from(final SmartLocation.ActivityRecognitionControl activityControl){
  return Observable.create(new ObservableOnSubscribe<DetectedActivity>(){
    @Override public void subscribe(    final ObservableEmitter<DetectedActivity> emitter) throws Exception {
      activityControl.start(new OnActivityUpdatedListener(){
        @Override public void onActivityUpdated(        DetectedActivity detectedActivity){
          emitter.onNext(detectedActivity);
        }
      }
);
    }
  }
).doOnDispose(new Action(){
    @Override public void run(){
      activityControl.stop();
    }
  }
);
}
