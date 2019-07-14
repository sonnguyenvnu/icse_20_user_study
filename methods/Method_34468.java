private Object[] toArgs(Collection<HystrixCollapser.CollapsedRequest<Object,Object>> requests){
  return new Object[]{collect(requests)};
}
