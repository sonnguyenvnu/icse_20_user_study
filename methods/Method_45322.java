private static <T>Function<InputStream,Iterable<T>> toObject(final CollectionType type){
  return new Function<InputStream,Iterable<T>>(){
    @Override public Iterable<T> apply(    final InputStream input){
      try (InputStream actual=input){
        String text=CharStreams.toString(new InputStreamReader(actual));
        return DEFAULT_MAPPER.readValue(text,type);
      }
 catch (      UnrecognizedPropertyException e) {
        logger.info("Unrecognized field: {}",e.getMessage());
        throw new MocoException(format("Unrecognized field [ %s ], please check!",e.getPropertyName()));
      }
catch (      JsonMappingException e) {
        logger.info("{} {}",e.getMessage(),e.getPathReference());
        throw new MocoException(e);
      }
catch (      IOException e) {
        throw new MocoException(e);
      }
    }
  }
;
}
