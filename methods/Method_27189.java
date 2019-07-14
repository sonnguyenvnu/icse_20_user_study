private String blockingDeviceName(){
  return (String)Observable.fromPublisher(s -> {
    DeviceName.with(App.getInstance()).request((info,error) -> {
      if (error == null && info != null)       s.onNext(info.marketName);
 else       s.onError(error);
    }
);
    s.onComplete();
  }
).blockingFirst(Build.MODEL);
}
