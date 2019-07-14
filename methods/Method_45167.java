public static Function<Resource,ResponseHandler> resourceToResourceHandler(){
  return new Function<Resource,ResponseHandler>(){
    @Override public ResponseHandler apply(    final Resource content){
      return Moco.with(content);
    }
  }
;
}
