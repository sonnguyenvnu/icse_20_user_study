private Function<Field,ResponseHandler> fieldToResponseHandler(final ResponseSetting response){
  return new Function<Field,ResponseHandler>(){
    @Override public ResponseHandler apply(    final Field field){
      try {
        Object value=field.get(response);
        return createResponseHandler(field.getName(),value);
      }
 catch (      IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }
;
}
