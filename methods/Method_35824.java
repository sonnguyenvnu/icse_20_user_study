public static <T extends Extension>Predicate<Map.Entry<String,Extension>> valueAssignableFrom(final Class<T> extensionType){
  return new Predicate<Map.Entry<String,Extension>>(){
    public boolean apply(    Map.Entry<String,Extension> input){
      return extensionType.isAssignableFrom(input.getValue().getClass());
    }
  }
;
}
