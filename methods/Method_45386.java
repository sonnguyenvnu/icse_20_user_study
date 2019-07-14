private Function<Map.Entry<String,Container>,ResponseHandler> toTargetHandler(final String name){
  return new Function<Map.Entry<String,Container>,ResponseHandler>(){
    @Override public ResponseHandler apply(    final Map.Entry<String,Container> pair){
      String result=COMPOSITES.get(name);
      if (result == null) {
        throw new IllegalArgumentException("unknown composite handler name [" + name + "]");
      }
      return createResponseHandler(pair,result);
    }
  }
;
}
