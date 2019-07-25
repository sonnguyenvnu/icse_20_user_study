public static Object convert(Object o){
  for (  Converter<?> converter : converters) {
    if (converter.appliesTo(o)) {
      return converter.convert(o);
    }
  }
  return o;
}
