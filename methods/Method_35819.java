@SuppressWarnings("unchecked") public static <T>Map<String,T> loadExtension(String... classNames){
  return (Map<String,T>)asMap(from(asList(classNames)).transform(toClasses()).transform(toExtensions()));
}
