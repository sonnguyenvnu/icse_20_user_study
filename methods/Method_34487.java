public HystrixInvokable create(MetaHolder metaHolder){
  HystrixInvokable executable;
  if (metaHolder.isCollapserAnnotationPresent()) {
    executable=new CommandCollapser(metaHolder);
  }
 else   if (metaHolder.isObservable()) {
    executable=new GenericObservableCommand(HystrixCommandBuilderFactory.getInstance().create(metaHolder));
  }
 else {
    executable=new GenericCommand(HystrixCommandBuilderFactory.getInstance().create(metaHolder));
  }
  return executable;
}
