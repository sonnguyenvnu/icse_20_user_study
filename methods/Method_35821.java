public static Map<String,Extension> load(Class<? extends Extension>... classes){
  return asMap(from(asList(classes)).transform(toExtensions()));
}
