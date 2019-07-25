@Override public void enhance(UriComponentsBuilder builder,MethodParameter parameter,Object value){
  if (value instanceof Pageable) {
    pagingResolver.enhance(builder,parameter,value);
  }
 else   if (value instanceof Sort) {
    sortResolver.enhance(builder,parameter,value);
  }
}
