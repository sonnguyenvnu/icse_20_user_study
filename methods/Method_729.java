protected void beforeBodyWriteInternal(FastJsonContainer container,MediaType contentType,MethodParameter returnType,ServerHttpRequest request,ServerHttpResponse response){
  FastJsonView annotation=returnType.getMethodAnnotation(FastJsonView.class);
  FastJsonFilter[] include=annotation.include();
  FastJsonFilter[] exclude=annotation.exclude();
  PropertyPreFilters filters=new PropertyPreFilters();
  for (  FastJsonFilter item : include) {
    filters.addFilter(item.clazz(),item.props());
  }
  for (  FastJsonFilter item : exclude) {
    filters.addFilter(item.clazz()).addExcludes(item.props());
  }
  container.setFilters(filters);
}
