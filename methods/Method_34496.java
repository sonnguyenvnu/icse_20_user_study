public List<HystrixProperty> getCollapserProperties(){
  return isCollapserAnnotationPresent() ? ImmutableList.copyOf(hystrixCollapser.collapserProperties()) : Collections.<HystrixProperty>emptyList();
}
