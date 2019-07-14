private List<Object> collect(Collection<HystrixCollapser.CollapsedRequest<Object,Object>> requests){
  List<Object> commandArgs=new ArrayList<Object>();
  for (  HystrixCollapser.CollapsedRequest<Object,Object> request : requests) {
    final Object[] args=(Object[])request.getArgument();
    commandArgs.add(args[0]);
  }
  return commandArgs;
}
