@Override public RequestContext clone(){
  Map<String,Object> localAttrs=new HashMap<>();
  localAttrs.putAll(this._localAttrs);
  return new RequestContext(Collections.synchronizedMap(localAttrs));
}
