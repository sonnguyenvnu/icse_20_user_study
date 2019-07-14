public HystrixInvokable createDelayed(MetaHolder metaHolder){
  HystrixInvokable executable;
  if (metaHolder.isObservable()) {
    executable=new GenericObservableCommand(HystrixCommandBuilderFactory.getInstance().create(metaHolder));
  }
 else {
    executable=new GenericCommand(HystrixCommandBuilderFactory.getInstance().create(metaHolder));
  }
  return executable;
}
