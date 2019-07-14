public static Map<String,Extension> asMap(Iterable<Extension> extensions){
  return Maps.uniqueIndex(extensions,new Function<Extension,String>(){
    public String apply(    Extension extension){
      return extension.getName();
    }
  }
);
}
