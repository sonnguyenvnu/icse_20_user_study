/** 
 * A simple logger for RxBus which can also prevent potential crash(OnErrorNotImplementedException) caused by error in the workflow.
 */
public static Subscriber<Object> defaultSubscriber(){
  return new Subscriber<Object>(){
    @Override public void onCompleted(){
      Log.d(TAG,"Duty off!!!");
    }
    @Override public void onError(    Throwable e){
      Log.e(TAG,"What is this? Please solve this as soon as possible!",e);
    }
    @Override public void onNext(    Object o){
      Log.d(TAG,"New event received: " + o);
    }
  }
;
}
